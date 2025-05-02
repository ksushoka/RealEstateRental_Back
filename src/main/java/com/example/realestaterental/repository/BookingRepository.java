package com.example.realestaterental.repository;

import com.example.realestaterental.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByGuestId(Integer id);
}
