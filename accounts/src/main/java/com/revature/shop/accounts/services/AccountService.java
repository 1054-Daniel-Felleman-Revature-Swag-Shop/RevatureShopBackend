package com.revature.shop.accounts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import com.revature.shop.MailService;
import com.revature.shop.accounts.repositories.AccountRepository;
import com.revature.shop.accounts.repositories.PointRepository;
import com.revature.shop.models.Account;
import com.revature.shop.models.PointHistory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Service
public class AccountService {
    private final AccountRepository repo;
    private final PointRepository pointsRepo;
    private final MailService mailService;
    private final TaskExecutor taskExecutor;

//    private String pointsEmailTemplate, saleEmailTemplate, newItemEmailTemplate;

    @Autowired
    public AccountService(AccountRepository repo, PointRepository pointsRepo, MailService mailService, TaskExecutor taskExecutor) throws IOException {
        this.repo = repo;
        this.pointsRepo = pointsRepo;
        this.mailService = mailService;
        this.taskExecutor = taskExecutor;

//        if (mailService != null) {
//            InputStream stream = getClass().getClassLoader().getResourceAsStream("points_email.html");
//            this.pointsEmailTemplate = StreamUtils.copyToString(stream, Charset.defaultCharset());
//            
//            stream = getClass().getClassLoader().getResourceAsStream("sale_email.html");
//            this.saleEmailTemplate = StreamUtils.copyToString(stream, Charset.defaultCharset());
//            
//            stream = getClass().getClassLoader().getResourceAsStream("new_item_email.html");
//            this.newItemEmailTemplate = StreamUtils.copyToString(stream, Charset.defaultCharset());
//            
//            stream.close();
//        }
    }

    @Transactional
    public boolean modPoints(String user, PointHistory change) {
        Account account = this.repo.findByEmail(user);
        if (account == null) return false;
        
        change.setAccount(account);
        if (account.getPoints() + change.getChange() < 0) return false;

        account.setPoints(account.getPoints() + change.getChange());
        this.repo.save(account);
        this.pointsRepo.save(change);

        if (change.getChange() > 0 && mailService != null) { //Only email if points added
//            taskExecutor.execute(() -> {
//                String email = pointsEmailTemplate.replaceAll("\\{\\{NAME}}", account.getName())
//                        .replaceAll("\\{\\{POINTS}}", String.valueOf(change.getChange()))
//                        .replaceAll("\\{\\{REASON}}", change.getCause());
//
//                mailService.sendRegistration(account.getEmail(), "RevatureShop Points", email);
//            });
        	this.taskExecutor.execute(() -> {
        		this.mailService.sendPointsEmail(account.getEmail(), account.getName(), String.valueOf(change.getChange()), change.getCause());
        	});
        }

        return true;
    }
    
    public boolean updateEmailSubscription(String user, boolean value) {
    	Account account = this.repo.findByEmail(user);
    	if (account == null) return false;
    	
    	account.setSubscribed(value);
    	this.repo.save(account);
    	
    	return true;
    }
}
