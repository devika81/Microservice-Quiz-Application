package com.example.STOCK_SERVICE.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "STOCK_PRICE")
public class StockPrice {

    @Id
    @Column(name = "STOCK_ID")
    private Integer stockID;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "COMPANY_PRICE")
    private Double companyPrice;

}
