package com.revature.shop.commerce.dtos;

import java.time.LocalDate;

public class PointChangeDto {

    private String cause;
    private int change;
    private LocalDate date;

    public PointChangeDto(String cause, int change) {
        this.cause = cause;
        this.change = change;
        date = LocalDate.now();
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public LocalDate getDate() {
        return date;
    }
}
