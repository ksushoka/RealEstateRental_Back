package com.example.realestaterental.controller;

import com.example.realestaterental.dto.BookingDTO;
import com.example.realestaterental.entity.Booking;
import com.example.realestaterental.entity.User;
import com.example.realestaterental.mapper.BookingMapper;
import com.example.realestaterental.service.BookingService;
import com.example.realestaterental.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "https://realestaterental-8ce5c.firebaseapp.com/"})
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;
    private final UserService userService;
//    @PostMapping("/save")
//    public ResponseEntity<Booking> bookProperty(
//            @AuthenticationPrincipal User userPrincipal,
//            @RequestParam Long propertyId,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate) {
//
//        User user = userService.getUserByUsername(userPrincipal.getUsername());
//        return ResponseEntity.ok(bookingService.bookProperty(user, propertyId, checkInDate, checkOutDate));
//    }
//    @GetMapping("/all")
//    public ResponseEntity<List<Booking>> getAllBookings(@AuthenticationPrincipal User userPrincipal) {
//        User user = userService.getUserByUsername(userPrincipal.getUsername());
//        return ResponseEntity.ok(bookingService.getBookingsForGuest(user));
//    }

    private final BookingMapper bookingMapper;

    @PostMapping("/save")
    public ResponseEntity<BookingDTO> bookProperty(
            @AuthenticationPrincipal User userPrincipal,
            @RequestParam Long propertyId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate) {

        User user = userService.getUserByUsername(userPrincipal.getUsername());
        Booking booking = bookingService.bookProperty(user, propertyId, checkInDate, checkOutDate);
        return ResponseEntity.ok(bookingMapper.toDto(booking));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookingDTO>> getAllBookings(@AuthenticationPrincipal User userPrincipal) {
        User user = userService.getUserByUsername(userPrincipal.getUsername());
        return ResponseEntity.ok(bookingMapper.toDtoList(bookingService.getBookingsForGuest(user)));
    }
}
