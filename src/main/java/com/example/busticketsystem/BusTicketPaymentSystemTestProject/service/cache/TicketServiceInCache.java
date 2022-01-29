package com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.cache;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.TicketNotFoundException;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.TicketService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    public Ticket getTicket(Long ticketId) throws TicketNotFoundException {
        return availableTicketMap
                .values().stream()
                .flatMap(Collection::stream)
                .filter(t -> ticketId.equals(t.getId()))
                .findFirst().orElseThrow(() -> new TicketNotFoundException(String.valueOf(ticketId)));
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        long id = ticket.getFlight().getId();

        ticket.setFlight(null);

        HashSet<Ticket> tickets = availableTicketMap.get(id);

        if (tickets != null) {
            tickets.add(ticket);
            return ticket;
        }
        availableTicketMap.computeIfAbsent(
                id,
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

        if (!CollectionUtils.isEmpty(tickets)) {
            Ticket ticket = tickets.stream()
                    .findFirst()
                    .orElse(null);

            tickets.remove(ticket);

            return Collections.singletonList(ticket);
        }

        return new ArrayList<>();
    }
}
