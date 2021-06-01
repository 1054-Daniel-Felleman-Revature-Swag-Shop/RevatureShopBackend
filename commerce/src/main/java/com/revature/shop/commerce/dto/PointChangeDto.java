package com.revature.shop.commerce.dto;

public class PointChangeDto {

    private String cause;
    private int change;

    public PointChangeDto(String cause, int change) {
        this.cause = cause;
        this.change = change;
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
}
