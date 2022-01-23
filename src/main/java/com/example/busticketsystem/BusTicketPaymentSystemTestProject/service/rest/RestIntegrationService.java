package com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.rest;


import com.example.busticketsystem.BusTicketPaymentSystemTestProject.dto.NewPaymentDTO;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestIntegrationService {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Long createPayment(int sum, String initials) {
        final String RESOURCE_URL = "http://localhost:8080/api/payments/";

        HttpEntity<NewPaymentDTO> request = new HttpEntity<>(new NewPaymentDTO(
                sum,
                initials)
        );

        return restTemplate.postForObject(RESOURCE_URL, request, Long.class);
    }

    public PaymentStatus getRandomPaymentStatus() {
        final String RESOURCE_URL = "http://localhost:8080/api/payments/status";

        return restTemplate.getForObject(RESOURCE_URL, PaymentStatus.class);
    }
}
