package com.example.realestaterental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Long id;
    private Long guestId;
    private Long propertyId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime bookingDate;
    private String status;
}
