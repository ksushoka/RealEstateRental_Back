package com.example.realestaterental.entity;

import javax.persistence.*;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private User guest;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    private String checkInDate;
    private String checkOutDate;
//
//    @OneToOne(mappedBy = "booking")
//    private Payment payment;
}
