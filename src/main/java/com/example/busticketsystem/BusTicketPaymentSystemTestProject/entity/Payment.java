package com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity;

import javax.persistence.*;

@Entity
@Table(name = "PAYMENT")
public class Payment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    //@Column(name = "payment_id")
    private long id;

    @OneToOne (optional=true, mappedBy="payment")
    private Ticket ticket;

    @Column(name = "STATUS")
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
