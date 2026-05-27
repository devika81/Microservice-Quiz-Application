package com.example.STOCK_SERVICE.exception;

//this file handles exception when the companyName provided by the user is wrong

public class StockNotFoundException extends RuntimeException{

    public StockNotFoundException(String message) {
        super(message);
    }
}
