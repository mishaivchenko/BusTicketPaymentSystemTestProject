package com.example.busticketsystem.BusTicketPaymentSystemTestProject.repository;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Payment;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("select p from Payment p where p.status = :status")
    List<Payment> findByStatus(@Param("status") PaymentStatus status);
}
