package com.revature.shop.commerce.exception;

public class UnableToCheckoutException extends Exception  {
    public UnableToCheckoutException(){ super("Shopper can not checkout his/her cart."); }
}
