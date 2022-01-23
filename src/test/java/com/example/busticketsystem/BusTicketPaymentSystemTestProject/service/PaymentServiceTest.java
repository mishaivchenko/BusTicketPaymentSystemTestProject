package com.example.busticketsystem.BusTicketPaymentSystemTestProject.service;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Payment;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers.PaymentStatus;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.repository.PaymentRepository;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.db.PaymentServiceInDb;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ComponentScan("com.example.busticketsystem.BusTicketPaymentSystemTestProject.*")
public class PaymentServiceTest {

    @Mock
    PaymentRepository paymentRepository;

    @InjectMocks
    PaymentServiceInDb paymentService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByStatusTestStatusMustBeEqualToRequested() {
        Payment paymentWithNewStatusAndIdEqualsOne = new Payment();
        paymentWithNewStatusAndIdEqualsOne.setId(1);
        paymentWithNewStatusAndIdEqualsOne.setStatus(PaymentStatus.NEW);

        when(paymentRepository.findByStatus(PaymentStatus.NEW))
                .thenReturn(Collections.singletonList(paymentWithNewStatusAndIdEqualsOne));

        assertEquals(1, paymentService.findByStatus(PaymentStatus.NEW).get(0).getId());
        assertEquals(PaymentStatus.NEW, paymentService.findByStatus(PaymentStatus.NEW).get(0).getStatus());
    }

    @Test
    public void findByStatusTestServiceMustReturnEmptyListIfPaymentWithRequestedStatusNotFound() {
        Payment paymentWithNewStatusAndIdEqualsOne = new Payment();
        paymentWithNewStatusAndIdEqualsOne.setId(1);
        paymentWithNewStatusAndIdEqualsOne.setStatus(PaymentStatus.NEW);

        when(paymentRepository.findByStatus(PaymentStatus.NEW))
                .thenReturn(Collections.singletonList(paymentWithNewStatusAndIdEqualsOne));

        assertEquals(Collections.emptyList().size(), paymentService.findByStatus(PaymentStatus.FAILED).size());
    }

}


