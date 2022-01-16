package com.example.busticketsystem.BusTicketPaymentSystemTestProject.service;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> findAll();

    Ticket getTicket(Long ticketId);

    Ticket saveTicket(Ticket ticket);

    void updateTicket(Ticket ticket);
}
