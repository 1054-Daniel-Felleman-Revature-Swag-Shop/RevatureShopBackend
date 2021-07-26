package com.revature.shop.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> updatePoints(@PathVariable String email, @RequestBody PointChange change) {
        return new ResponseEntity<>(service.modPoints(email, change) ? HttpStatus.ACCEPTED : HttpStatus.NOT_ACCEPTABLE);
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
    public List<PointChange> pointHistory(@PathVariable int id){
        Account account = repo.findAccountById(id);
        System.out.println(account);
        return pr.findPointChangeByAccount(account);
    }
}