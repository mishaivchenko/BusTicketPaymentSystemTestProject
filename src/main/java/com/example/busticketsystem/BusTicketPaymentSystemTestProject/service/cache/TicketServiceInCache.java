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

    Map<Long, Set<Ticket>> availableTicketMap = new HashMap<>();

    @Override
    public Set<Ticket> getAll(Long key) {
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

        Set<Ticket> tickets = availableTicketMap.get(id);

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
        Set<Ticket> tickets = availableTicketMap.get(flightId);

        if (!CollectionUtils.isEmpty(tickets)) {
            Ticket ticket = tickets.stream()
                    .findFirst()
                    .orElse(null);

            tickets.remove(ticket);

            return Collections.singletonList(ticket);
        }

        return new ArrayList<>();
    }

    @Override
    public Long getFlightIdBuyTicketId(long ticketId) {
        Map.Entry<Long, Set<Ticket>> entry = availableTicketMap.entrySet().stream()
                .filter(e -> e.getValue().stream().anyMatch(t -> t.getId() == ticketId))
                .findAny().orElse(null);

        return entry == null ? 0L : entry.getKey();

    }
}
