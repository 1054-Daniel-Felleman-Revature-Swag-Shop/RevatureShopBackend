package com.revature.shop.accounts.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.shop.MailService;
import com.revature.shop.accounts.repositories.AccountRepository;
import com.revature.shop.accounts.repositories.PointRepository;
import com.revature.shop.models.Account;
import com.revature.shop.models.PointHistory;

@Service
public class AccountService {
    private final AccountRepository repo;
    private final PointRepository pointsRepo;
    private final MailService mailService;
    private final TaskExecutor taskExecutor;

    @Autowired
    public AccountService(AccountRepository repo, PointRepository pointsRepo, MailService mailService, TaskExecutor taskExecutor) throws IOException {
        this.repo = repo;
        this.pointsRepo = pointsRepo;
        this.mailService = mailService;
        this.taskExecutor = taskExecutor;
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

        if (change.getChange() > 0 && this.mailService != null) { //Only email if points added
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
    	
    	return value;
    }
    
    public List<Account> getSubscribedAccounts() {
    	return this.repo.findAllBySubscribedTrue();
    }
    
    public Account getByEmail(String email) {
    	return this.repo.findByEmail(email);
    }
    
    public List<Account> getAll() {
    	return this.repo.findAll();
    }
    
    public Account getById(int id) {
    	return this.repo.findAccountById(id);
    }
    
    public List<PointHistory> getPointHistory(int accountId) {
    	return this.pointsRepo.findPointChangeByAccount(this.getById(accountId));
    }
}
