package com.revature.shop.commerce.exceptions;

public class ItemOutOfStockException extends Exception {

    public ItemOutOfStockException() {
        super("Item Out Of Stock");
    }
}
