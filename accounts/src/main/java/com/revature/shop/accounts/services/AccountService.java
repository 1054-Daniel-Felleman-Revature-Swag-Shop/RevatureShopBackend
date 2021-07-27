package com.revature.shop.accounts.services;

//import com.revature.accounts.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import com.revature.shop.MailService;
import com.revature.shop.accounts.models.Account;
import com.revature.shop.accounts.models.PointHistory;
import com.revature.shop.accounts.repositories.AccountRepository;
import com.revature.shop.accounts.repositories.PointRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Service
public class AccountService {
    private final AccountRepository repo;
    private final PointRepository pointsRepo;
    private final MailService mailService;
    private final TaskExecutor taskExecutor;

    private String emailTemplate;

    @Autowired
    public AccountService(AccountRepository repo, PointRepository pointsRepo, MailService mailService, TaskExecutor taskExecutor) throws IOException {
        this.repo = repo;
        this.pointsRepo = pointsRepo;
        this.mailService = mailService;
        this.taskExecutor = taskExecutor;

        if (mailService != null) {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("points_email.html");
            emailTemplate = StreamUtils.copyToString(stream, Charset.defaultCharset());
        }
    }

    @Transactional
    //@HystrixCommand(fallbackMethod = "downService")
    public boolean modPoints(String user, PointHistory change) {
        Account account = repo.findByEmail(user);
        change.setAccount(account);
        if (account == null) {
            return false;
        }

        if (account.getPoints() + change.getChange() < 0) {
            return false;
        }

        account.setPoints(account.getPoints() + change.getChange());
        repo.save(account);
        pointsRepo.save(change);

        if (change.getChange() > 0 && mailService != null) { //Only email if points added
            taskExecutor.execute(() -> {
                String email = emailTemplate.replaceAll("\\{\\{NAME}}", account.getName())
                        .replaceAll("\\{\\{POINTS}}", String.valueOf(change.getChange()))
                        .replaceAll("\\{\\{REASON}}", change.getCause());

                mailService.sendRegistration(account.getEmail(), "RevatureShop Points", email);
            });
        }

        return true;
    }

    public String downService() {
        return "The Accounts service is currently under maintenance, please check in later";
    }
}