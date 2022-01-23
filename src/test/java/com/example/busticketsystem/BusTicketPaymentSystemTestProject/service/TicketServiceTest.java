package com.example.busticketsystem.BusTicketPaymentSystemTestProject.service;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.TicketNotFoundException;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.repository.TicketRepository;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.db.TicketServiceInDb;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ComponentScan("com.example.busticketsystem.BusTicketPaymentSystemTestProject.*")
public class TicketServiceTest {

    @Mock
    TicketRepository ticketRepository;

    @InjectMocks
    TicketServiceInDb ticketService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = TicketNotFoundException.class)
    public void whenTicketIdIsIncorrectThenThrowTicketNotFoundException() {
        when(ticketRepository.getOne(0L)).thenReturn(null);

        ticketService.getTicket(0L);
    }

    @Test
    public void findByFlightIdTest() {
        //Given
        Flight flight = new Flight();
        flight.setId(1);

        Ticket ticket = new Ticket();
        ticket.setFlight(flight);

        when(ticketRepository.findByFlightId(1L))
                .thenReturn(Collections.singletonList(ticket));

        //Then
        assertEquals(flight.getId(), ticketService.getTicketByFlightId(1L).get(0).getFlight().getId());
    }

}
