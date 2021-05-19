package com.revature.shop.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository repo;

    @Autowired
    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public boolean modPoints(int userId, int change) {
        Optional<Account> optional = repo.findById(userId);

        if (optional.isEmpty()) {
            return false;
        }

        Account account = optional.get();

        if (account.getPoints() + change < 0) {
            return false;
        }

        account.setPoints(account.getPoints() + change);
        repo.save(account);

        return true;
    }
}