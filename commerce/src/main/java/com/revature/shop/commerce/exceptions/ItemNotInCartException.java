package com.revature.shop.commerce.exceptions;

public class ItemNotInCartException extends Exception {

    public ItemNotInCartException() {
        super("Item not present in cart");
    }

}
