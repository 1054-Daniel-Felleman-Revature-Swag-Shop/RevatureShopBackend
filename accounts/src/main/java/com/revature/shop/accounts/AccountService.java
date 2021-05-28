package com.revature.shop.accounts;

import com.revature.shop.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository repo;
    private final PointRepository pointsRepo;
    private final MailService mailService;
    private final TaskExecutor taskExecutor;

    private String emailTemplate;

    @Autowired
    public AccountService(AccountRepository repo, PointRepository pointsRepo, MailService mailService, TaskExecutor taskExecutor) {
        this.repo = repo;
        this.pointsRepo = pointsRepo;
        this.mailService = mailService;
        this.taskExecutor = taskExecutor;

        if (mailService != null) {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("points_email.html");

            try {
                emailTemplate = StreamUtils.copyToString(stream, Charset.defaultCharset());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Transactional
    //@HystrixCommand(fallbackMethod = "downService")
    public boolean modPoints(int userId, PointChange change) {
        Optional<Account> optional = repo.findById(userId);

        if (optional.isEmpty()) {
            return false;
        }

        Account account = optional.get();

        if (account.getPoints() + change.change() < 0) {
            return false;
        }

        account.setPoints(account.getPoints() + change.change());
        repo.save(account);
        pointsRepo.save(change);

        if (change.change() > 0 && mailService != null) { //Only email if points added
            taskExecutor.execute(() -> {
                String email = emailTemplate.replaceAll("\\{\\{NAME}}", account.getName())
                        .replaceAll("\\{\\{POINTS}}", String.valueOf(change.change()))
                        .replaceAll("\\{\\{REASON}}", change.cause());

                mailService.sendRegistration(account.getEmail(), "RevatureShop Points", email);
            });
        }

        return true;
    }

    public String downService() {
        return "The Accounts service is currently under maintenance, please check in later";
    }
}