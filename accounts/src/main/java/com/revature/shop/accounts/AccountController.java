package com.revature.shop.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService service;
    private final AccountRepository repo;

    @Autowired
    public AccountController(AccountService service, AccountRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @PostMapping("/points/{id}")
    public ResponseEntity<?> updatePoints(@PathVariable int id, @RequestBody PointChange change) {
        return new ResponseEntity<>(service.modPoints(id, change) ? HttpStatus.ACCEPTED : HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping(value = "/dummylogin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody Account dummyLogin(@RequestBody String email) {
        return service.getAccount(email);
    }

    @GetMapping
    public ResponseEntity<Account> loggedIn() {
        return null;
    }

    @GetMapping("/all")
    public List<Account> allUsers() {
        return repo.findAll();
    }
}