package com.example.STOCK_CALCULATION_SERVICE.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("STOCK-SERVICE")
public interface StockInterface {

    @GetMapping("/api/get/price/by_company_name/{companyName}")
    public ResponseEntity <Double> fetchByCompanyName(@PathVariable String companyName);


}
