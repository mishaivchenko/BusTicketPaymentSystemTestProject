package com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.cache;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.TicketNotFoundException;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TicketServiceInCache implements TicketService {

    Map<Long, HashSet<Ticket>> availableTicketMap = new HashMap<>();

    @Override
    public HashSet<Ticket> getAll(Long key) {
        return availableTicketMap.getOrDefault(key, new HashSet<>());
    }

    @Override
    public List<Ticket> findAll() {
        return availableTicketMap.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public Ticket getTicket(Long ticketId) {
        return availableTicketMap
                .values().stream()
                .flatMap(Collection::stream)
                .filter(t -> ticketId.equals(t.getId()))
                .findFirst().orElseThrow(() -> new TicketNotFoundException(String.valueOf(ticketId)));
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        HashSet<Ticket> tickets = availableTicketMap.get(ticket.getFlight().getId());

        if (tickets != null) {
            tickets.add(ticket);
            return ticket;
        }
        availableTicketMap.computeIfAbsent(
                ticket.getFlight().getId(),
                k -> new HashSet<>(Collections.singletonList(ticket))
        );
        return ticket;
    }

    @Override
    public void updateTicket(Ticket ticket) {
        saveTicket(ticket);
    }

    @Override
    public List<Ticket> getTicketByFlightId(Long flightId) {
        HashSet<Ticket> tickets = availableTicketMap.get(flightId);

        if (tickets != null) {
            Ticket ticket = tickets.stream()
                    .findFirst()
                    .orElse(null);
            tickets.remove(ticket);
            return Collections.singletonList(ticket);
        }

        return new ArrayList<>();
    }
}
