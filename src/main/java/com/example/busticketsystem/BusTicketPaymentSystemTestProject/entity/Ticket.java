package com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity;

import javax.persistence.*;

@Entity
@Table(name = "TICKET")
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @OneToOne (optional=true, cascade=CascadeType.ALL)
    private Payment payment;

    @Column(name = "OWNER")
    private String owner;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
}
