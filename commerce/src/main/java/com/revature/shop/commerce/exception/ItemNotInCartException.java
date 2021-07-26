package com.revature.shop.commerce.exception;

public class ItemNotInCartException extends Exception {

    public ItemNotInCartException() {
        super("Item not present in cart");
    }

}
