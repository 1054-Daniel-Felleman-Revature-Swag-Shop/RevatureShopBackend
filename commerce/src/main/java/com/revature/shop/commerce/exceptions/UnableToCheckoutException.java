package com.revature.shop.commerce.exceptions;

public class UnableToCheckoutException extends Exception  {
    public UnableToCheckoutException(){ super("Shopper can not checkout his/her cart."); }
}
