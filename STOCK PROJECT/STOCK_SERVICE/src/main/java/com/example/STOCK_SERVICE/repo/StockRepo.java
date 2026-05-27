package com.example.STOCK_SERVICE.repo;

import com.example.STOCK_SERVICE.model.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface StockRepo extends JpaRepository <StockPrice,Integer> {

    //this returns an object of StockPrice and not the exact expected output, ie; companyPrice
    public StockPrice findByCompanyName(String companyName);

}
