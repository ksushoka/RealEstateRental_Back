package com.example.realestaterental.entity;

import javax.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String paymentMethod;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
