package com.example.busticketsystem.BusTicketPaymentSystemTestProject.facade;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Payment;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.EmptyInitialsException;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.TicketOutOfStockException;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.base.BusinessLogicException;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.FlightService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.PaymentService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.TicketService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.rest.RestIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderTicketFacade {

    private FlightService flightService;
    private TicketService ticketServiceInDb;
    private PaymentService paymentService;
    private RestIntegrationService restIntegrationService;
    private TicketService ticketServiceInCache;


    @Autowired
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    @Autowired
    public void setTicketServiceInDb(TicketService ticketServiceInDb) {
        this.ticketServiceInDb = ticketServiceInDb;
    }

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Autowired
    public void setRestIntegrationService(RestIntegrationService restIntegrationService) {
        this.restIntegrationService = restIntegrationService;
    }

    @Autowired
    public void setTicketServiceInCache(@Qualifier("ticketServiceInCache") TicketService ticketServiceInCache) {
        this.ticketServiceInCache = ticketServiceInCache;
    }

    /**
     * @param flightId - flight for which the ticket was purchased
     * @param initials - client initials
     * @return new ordered ticket with payment status NEW
     * @throws EmptyInitialsException
     * @throws TicketOutOfStockException
     */
    public Ticket orderTicket(long flightId, String initials) throws BusinessLogicException {

        checkInitials(initials);

        Flight flight = flightService.getFlight(flightId);

        checkAvailableTicketsCount(flight);

        Ticket ticket = getTicket(flight, initials);

        flight.addTicket(ticket);

        Payment payment = createPayment(initials, flight);

        ticket.setPayment(payment);
        ticketServiceInDb.saveTicket(ticket);
        flightService.saveFlight(flight);

        return ticket;
    }

    /**
     * @param initials - client initials
     * @param flight   - - flight for which the payment was purchased
     * @return payment with NEW status what created thought web service
     * controller - PaymentController
     * api - /api/payments/
     */
    private Payment createPayment(String initials, Flight flight) {
        Long paymentId = restIntegrationService.createPayment(flight.getPrice(), initials);

        return paymentService.getPayment(paymentId);
    }

    /**
     * @param flight   - flight for which the ticket ordered
     * @param initials - client initials
     * @return ticket from cache (already ordered ticket with FAILED payment status).
     * if cache is empty - create new Ticket
     */
    private Ticket getTicket(Flight flight, String initials) {
        Ticket ticket = ticketServiceInCache.getTicketByFlightId(flight.getId()).stream()
                .findFirst()
                .orElse(new Ticket());

        ticket.setOwner(initials);
        ticket.setFlight(flight);

        return ticketServiceInDb.saveTicket(ticket);
    }

    /**
     * @param flight - check is flight has available ticket
     *               if all tickets are out of stock throws TicketOutOfStockException
     */

    private void checkAvailableTicketsCount(Flight flight) throws TicketOutOfStockException {
        if (flight.getTickets().size() == flight.getCount() &&
                ticketServiceInCache.getAll(flight.getId()).isEmpty()) {

            throw new TicketOutOfStockException(String.valueOf(flight.getId()));
        }
    }

    /**
     * @param initials - client initials
     *                 if the client initials is empty throws EmptyInitialsException
     */
    private void checkInitials(String initials) throws EmptyInitialsException {
        if (initials.isEmpty()) throw new EmptyInitialsException();
    }
}
