package com.example.realestaterental.controller;

import com.example.realestaterental.entity.Booking;
import com.example.realestaterental.entity.User;
import com.example.realestaterental.service.BookingService;
import com.example.realestaterental.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;
    private final UserService userService;
    @PostMapping("/save")
    public ResponseEntity<Booking> bookProperty(@AuthenticationPrincipal User userPrincipal,
                                                @RequestParam Long propertyId,
                                                @RequestParam LocalDateTime checkInDate,
                                                @RequestParam LocalDateTime checkOutDate) {
        User user = userService.getUserByUsername(userPrincipal.getUsername());
        return ResponseEntity.ok(bookingService.bookProperty(user,propertyId, checkInDate, checkOutDate));
    }
}
