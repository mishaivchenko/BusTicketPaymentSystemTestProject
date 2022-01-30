package com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.db;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.TicketNotFoundException;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.repository.TicketRepository;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TicketServiceInDb implements TicketService {
    private TicketRepository ticketRepository;

    @Autowired
    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicket(Long ticketId) throws TicketNotFoundException {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException(String.valueOf(ticketId)));
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getTicketByFlightId(Long flightId) {
        return ticketRepository.findByFlightId(flightId);
    }

    @Override
    public Set<Ticket> getAll(Long key) {
        throw new UnsupportedOperationException("Not implemented. Implement me if needed");
    }

    @Override
    public Long getFlightIdBuyTicketId(long ticketId) {
        throw new UnsupportedOperationException("Not implemented. Implement me if needed");
    }
}
