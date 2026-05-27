package com.example.service_registry_for_stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryForStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryForStockApplication.class, args);
	}

}
