package com.revature.shop.accounts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.shop.accounts.models.Account;
import com.revature.shop.accounts.models.PointHistory;
import com.revature.shop.accounts.repositories.AccountRepository;
import com.revature.shop.accounts.repositories.PointRepository;
import com.revature.shop.accounts.services.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService service;
    private final AccountRepository repo;
    private final PointRepository pr;

    @Autowired
    public AccountController(AccountService service, AccountRepository repo, PointRepository pr) {
        this.service = service;
        this.repo = repo;
        this.pr = pr;
    }

    @PostMapping("/points/{email}")
    public ResponseEntity<?> updatePoints(@PathVariable String email, @RequestBody PointHistory change) {
    	boolean success = this.service.modPoints(email, change);
    	return new ResponseEntity<>(success, success ? HttpStatus.ACCEPTED : HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping(value = "/dummylogin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Account> dummyLogin(@RequestBody MultiValueMap<String, String> form) {
        return new ResponseEntity<>(repo.findByEmail(form.getFirst("email")), HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public List<Account> allUsers() {
        return repo.findAll();
    }

    @GetMapping("pointHistory/{id}")
    public List<PointHistory> pointHistory(@PathVariable int id) {
        Account account = repo.findAccountById(id);
        System.out.println(account);
        return pr.findPointChangeByAccount(account);
    }
}