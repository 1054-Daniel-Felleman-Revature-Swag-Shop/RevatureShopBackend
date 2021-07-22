package com.revature.shop.accounts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.revature.shop.accounts.models.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByEmail(String email);

    Account findAccountById(int id);
}