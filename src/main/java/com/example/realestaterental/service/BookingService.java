package com.example.realestaterental.service;

import com.example.realestaterental.entity.Booking;
import com.example.realestaterental.entity.Property;
import com.example.realestaterental.entity.User;
import com.example.realestaterental.repository.BookingRepository;
import com.example.realestaterental.repository.PropertyRepository;
import com.example.realestaterental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingService {
    private final BookingRepository bookingRepository;

    private final UserRepository userRepository;

    private final PropertyRepository propertyRepository;
    public Booking bookProperty(User guest, Long propertyId, LocalDate checkInDate, LocalDate checkOutDate) {
        Property property = propertyRepository.findById(propertyId).get();
        Booking booking = new Booking();
        booking.setBookingDate(LocalDateTime.now());
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setGuest(guest);
        booking.setProperty(property);
        return bookingRepository.save(booking);
    }
}
