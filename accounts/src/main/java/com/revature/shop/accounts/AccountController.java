package com.revature.shop.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping(value = "/dummylogin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Account> dummyLogin(@RequestBody MultiValueMap<String, String> form) {
        return new ResponseEntity<>(repo.findByEmail(form.getFirst("email")), HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public List<Account> allUsers() {
        return repo.findAll();
    }
}