package com.example.STOCK_SERVICE.controller;

import com.example.STOCK_SERVICE.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StockController {

    @Autowired
    StockService service;

    @GetMapping("/get/price/by_company_name/{companyName}")
    public Double fetchByCompanyName(@PathVariable String companyName)
    {
        return service.fetchByCompanyName(companyName);
    }

}
