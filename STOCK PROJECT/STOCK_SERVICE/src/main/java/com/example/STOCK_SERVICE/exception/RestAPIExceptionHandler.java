package com.example.STOCK_SERVICE.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//to handle exceptions getting at controller level

@RestControllerAdvice
public class RestAPIExceptionHandler {

    @ExceptionHandler(value = StockNotFoundException.class)
    public ResponseEntity <String> handleCompanyNotFoundException(StockNotFoundException se)
    {
        return new ResponseEntity<>(se.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
