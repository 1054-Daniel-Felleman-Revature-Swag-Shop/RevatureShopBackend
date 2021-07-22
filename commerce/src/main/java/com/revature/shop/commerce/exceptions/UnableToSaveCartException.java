package com.revature.shop.commerce.exceptions;

public class UnableToSaveCartException extends Exception  {

    public UnableToSaveCartException(){ super("Cart can not be persisted"); }

}
