package com.example.busticketsystem.BusTicketPaymentSystemTestProject.service;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.TicketNotFoundException;

import java.util.List;
import java.util.Set;

public interface TicketService {
    List<Ticket> findAll();

    Ticket getTicket(Long ticketId) throws TicketNotFoundException;

    Ticket saveTicket(Ticket ticket);

    void updateTicket(Ticket ticket);

    List<Ticket> getTicketByFlightId(Long flightId);

    Set<Ticket> getAll(Long key);

    Long getFlightIdBuyTicketId(long ticketId);


}
