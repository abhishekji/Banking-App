package com.example.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BankingShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankingShopApplication.class, args);
    }
}
