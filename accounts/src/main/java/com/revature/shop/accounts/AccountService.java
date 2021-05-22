package com.revature.shop.accounts;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository repo;
    private final PointRepository pointsRepo;

    @Autowired
    public AccountService(AccountRepository repo, PointRepository pointsRepo) {
        this.repo = repo;
        this.pointsRepo = pointsRepo;
    }

    @Transactional
    @HystrixCommand(fallbackMethod = "downService")
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

        return true;
    }

    public String downService(){
        return "The Accounts service is currently under maintenance, please check in later";
    }
}