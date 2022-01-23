package com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers.PaymentStatus;

import javax.persistence.*;

@Entity
@Table(name = "PAYMENTS")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(mappedBy = "payment")
    private Ticket ticket;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private PaymentStatus status;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "OWNER")
    private String owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String initials) {
        this.owner = initials;
    }
}
