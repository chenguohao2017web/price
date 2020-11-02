package com.building.prices;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.building.prices.mapper")
public class PricesApplication {
    public static void main(String[] args) {
        SpringApplication.run(PricesApplication.class, args);
    }

}
