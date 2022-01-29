package com.example.busticketsystem.BusTicketPaymentSystemTestProject.service;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.TicketNotFoundException;

import java.util.HashSet;
import java.util.List;

public interface TicketService {
    List<Ticket> findAll();

    Ticket getTicket(Long ticketId) throws TicketNotFoundException;

    Ticket saveTicket(Ticket ticket);

    void updateTicket(Ticket ticket);

    List<Ticket> getTicketByFlightId(Long flightId);

    HashSet<Ticket> getAll(Long key);


}
