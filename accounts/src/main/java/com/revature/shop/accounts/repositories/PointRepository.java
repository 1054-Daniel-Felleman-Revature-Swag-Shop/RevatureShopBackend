package com.revature.shop.accounts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.revature.shop.accounts.models.Account;
import com.revature.shop.accounts.models.PointHistory;

import java.util.List;
import java.util.Optional;

public interface PointRepository extends JpaRepository<PointHistory, Integer> {


    List<PointHistory> findPointChangeByAccount(Account account);
}

