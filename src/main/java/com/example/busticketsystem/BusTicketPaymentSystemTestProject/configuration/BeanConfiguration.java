package com.example.busticketsystem.BusTicketPaymentSystemTestProject.configuration;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers.PaymentStatusProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PaymentStatusProvider paymentStatusService(){
        return new PaymentStatusProvider();
    }
}
