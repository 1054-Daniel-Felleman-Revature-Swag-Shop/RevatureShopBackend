package com.revature.shop.accounts;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Entity;
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

    private int points;

    @OneToMany(mappedBy = "account")
    private List<PointChange> pointHistory;

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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<PointChange> getPointHistory() {
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