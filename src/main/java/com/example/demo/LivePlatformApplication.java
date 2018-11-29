package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class LivePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(LivePlatformApplication.class, args);
    }
}
