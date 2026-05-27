package com.example.STOCK_SERVICE.service;

import com.example.STOCK_SERVICE.exception.StockNotFoundException;
import com.example.STOCK_SERVICE.model.StockPrice;
import com.example.STOCK_SERVICE.repo.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    StockRepo repo;

    public Double fetchByCompanyName(String companyName)
    {
        StockPrice sp = repo.findByCompanyName(companyName);

        //handles exception
        //if the companyName provided by user is wrong, no companyPrice will be fetched and thus
        //the below exception will be thrown
        if(sp == null)
        {
            throw new StockNotFoundException("Stock price not found for the given company!");
        }
        return sp.getCompanyPrice();
    }

}
