package com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "FLIGHTS")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "START")
    private String start;
    @Column(name = "TO")
    private String to;
    @Column(name = "DATE")
    private LocalDate date;
    @Column(name = "PRICE")
    private int price;
    @Column
    private int count;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private final Set<Ticket> tickets = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFrom() {
        return start;
    }

    public void setFrom(String start) {
        this.start = start;
    }

    public String getTo() {
        return to;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        ticket.setFlight(this);
    }

    public boolean removeTicket(Ticket ticket) {
        ticket.setFlight(null);
        return tickets.remove(ticket);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
