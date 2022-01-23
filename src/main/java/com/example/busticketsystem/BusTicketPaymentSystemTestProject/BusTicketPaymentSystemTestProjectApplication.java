package com.example.busticketsystem.BusTicketPaymentSystemTestProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.example.busticketsystem.BusTicketPaymentSystemTestProject.*"})
public class BusTicketPaymentSystemTestProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusTicketPaymentSystemTestProjectApplication.class, args)
    }
}
