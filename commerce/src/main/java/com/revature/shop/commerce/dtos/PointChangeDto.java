package com.revature.shop.commerce.dtos;

import java.time.LocalDate;

public class PointChangeDto {

    private String cause;
    private double change;
    private LocalDate date;

    public PointChangeDto(String cause, double currentPurchaseTotal) {
        this.cause = cause;
        this.change = currentPurchaseTotal;
        date = LocalDate.now();
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public LocalDate getDate() {
        return date;
    }
}
