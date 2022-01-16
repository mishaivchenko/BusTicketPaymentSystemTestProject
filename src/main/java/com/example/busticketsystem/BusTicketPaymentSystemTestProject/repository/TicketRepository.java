package com.example.busticketsystem.BusTicketPaymentSystemTestProject.repository;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
