package com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity;

import javax.persistence.*;

@Entity
@Table(name = "TICKETS")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Payment payment;

    @Column(name = "OWNER")
    private String owner;

    @ManyToOne(fetch = FetchType.LAZY)
    private Flight flight;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        return id == ticket.id;
    }

}
