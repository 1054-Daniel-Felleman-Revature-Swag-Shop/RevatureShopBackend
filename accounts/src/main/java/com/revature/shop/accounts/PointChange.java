package com.revature.shop.accounts;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "point_history")
public final class PointChange {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "fk_account")
    private Account account;

    private String cause;
    private int change;

    public PointChange() {}

    public PointChange(String cause, int change) {
        this.cause = cause;
        this.change = change;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public String cause() { return cause; }

    public int change() { return change; }

    @Override
    public String toString() {
        return "PointChange[" +
                "cause=" + cause + ", " +
                "change=" + change + ']';
    }
}