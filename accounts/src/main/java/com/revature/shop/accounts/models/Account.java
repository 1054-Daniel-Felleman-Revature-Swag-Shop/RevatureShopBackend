package com.revature.shop.accounts.models;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String name;

    private Role role;

    private double points;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<PointHistory> pointHistory;

    public Account() { }

    public Account(int id, String email, int points) {
        this.id = id;
        this.email = email;
        this.points = points;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public int getId(){
        return this.id;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double d) {
        this.points = d;
    }

    public List<PointHistory> getPointHistory() {
        return pointHistory;
    }

    enum Role {
        USER, ADMIN;

        @JsonValue
        public int toValue() {
            return ordinal();
        }
    }
}