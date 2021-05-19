package com.revature.shop.accounts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String email;

    private int points;

    public Account() { }

    public Account(int id, String email, int points) {
        this.id = id;
        this.email = email;
        this.points = points;
    }

    public String getEmail() {
        return email;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}