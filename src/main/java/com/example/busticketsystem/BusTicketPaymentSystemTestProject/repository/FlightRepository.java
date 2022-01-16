package com.example.busticketsystem.BusTicketPaymentSystemTestProject.repository;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
