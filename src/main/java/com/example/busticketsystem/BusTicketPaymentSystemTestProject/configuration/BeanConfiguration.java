package com.example.busticketsystem.BusTicketPaymentSystemTestProject.configuration;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers.PaymentStatusProvider;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean
    public PaymentStatusProvider paymentStatusService() {
        return new PaymentStatusProvider();
    }

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder().build();
    }

}
