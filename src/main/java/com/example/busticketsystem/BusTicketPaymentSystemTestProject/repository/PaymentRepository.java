package com.example.busticketsystem.BusTicketPaymentSystemTestProject.repository;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Employee;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
