package com.example.STOCK_CALCULATION_SERVICE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StockCalculationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockCalculationServiceApplication.class, args);
	}

}
