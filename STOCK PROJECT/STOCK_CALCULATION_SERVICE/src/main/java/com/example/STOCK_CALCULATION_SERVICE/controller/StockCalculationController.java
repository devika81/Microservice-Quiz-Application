package com.example.STOCK_CALCULATION_SERVICE.controller;

import com.example.STOCK_CALCULATION_SERVICE.feign.StockInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StockCalculationController {

    @Autowired
    StockInterface stockInterface;

    @GetMapping("/get/totalPrice/{companyName}/{quantity}")
    public ResponseEntity <?> calculateTotalPrice(@PathVariable String companyName,
                                                       @PathVariable Integer quantity)
    {
        ResponseEntity <?> responseEntity = null;
        Double totalPrice = null;

        try
        {
            responseEntity = stockInterface.fetchByCompanyName(companyName);
            int status = responseEntity.getStatusCode().value();

            if(status == 200)
            {
                Double price = (Double) responseEntity.getBody();
                totalPrice = price * quantity;
                String response = "The total price is: " +totalPrice;
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            }
        }

        catch (Exception e)
        {
            responseEntity = new ResponseEntity<>("Company not found!", HttpStatus.NOT_FOUND);
        }

        return responseEntity;

    }

}
