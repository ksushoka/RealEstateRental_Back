package com.example.realestaterental.entity;

import com.example.realestaterental.entity.type.StatusType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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

    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    private StatusType status;//confirmed\cancelled
//
//    @OneToOne(mappedBy = "booking")
//    private Payment payment;
}
