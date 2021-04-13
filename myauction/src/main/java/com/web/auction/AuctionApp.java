package com.web.auction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.web.auction.mapper")
public class AuctionApp {
    public static void main(String[] args) {
        SpringApplication.run(AuctionApp.class,args);
    }
}
