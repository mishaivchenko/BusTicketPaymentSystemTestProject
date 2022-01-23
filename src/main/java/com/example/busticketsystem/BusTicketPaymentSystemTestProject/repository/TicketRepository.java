package com.example.busticketsystem.BusTicketPaymentSystemTestProject.repository;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("select t from Ticket t where t.flight.id = :flight_id")
    List<Ticket> findByFlightId(@Param("flight_id") Long flight_id);
}
