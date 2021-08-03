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

import com.revature.shop.accounts.services.AccountService;
import com.revature.shop.models.Account;
import com.revature.shop.models.PointHistory;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping("/points/{email}")
    public ResponseEntity<?> updatePoints(@PathVariable String email, @RequestBody PointHistory change) {
    	boolean updatedPoints = this.service.modPoints(email, change);
        return new ResponseEntity<>(updatedPoints, updatedPoints ? HttpStatus.ACCEPTED : HttpStatus.NOT_ACCEPTABLE);
    }
    
    @PostMapping("/subscribe/{email}/{value}")
    public ResponseEntity<?> updateEmailSubscription(@PathVariable String email, @PathVariable Boolean value) {
    	return new ResponseEntity<>(this.service.updateEmailSubscription(email, value), HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/subscribed")
    public ResponseEntity<List<Account>> getSubscribedAccounts() {
    	return new ResponseEntity<>(this.service.getSubscribedAccounts(), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/dummylogin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Account> dummyLogin(@RequestBody MultiValueMap<String, String> form) {
        return new ResponseEntity<>(this.service.getByEmail(form.getFirst("email")), HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/{email}")
    public ResponseEntity<Account> getAccount(@PathVariable String email) {
    	return new ResponseEntity<>(this.service.getByEmail(email), HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> allUsers() {
    	return new ResponseEntity<>(this.service.getAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("pointHistory/{id}")
    public ResponseEntity<List<PointHistory>> pointHistory(@PathVariable int id) {
        return new ResponseEntity<>(this.service.getPointHistory(id), HttpStatus.ACCEPTED);
    }
}