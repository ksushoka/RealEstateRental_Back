package com.example.realestaterental.controller;

import com.example.realestaterental.dto.ReviewResponseDTO;
import com.example.realestaterental.entity.Review;
import com.example.realestaterental.entity.User;
import com.example.realestaterental.service.ReviewService;
import com.example.realestaterental.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "https://realestaterental-8ce5c.firebaseapp.com/"})
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;
    @PostMapping("/save/{propertyId}")
    public void save(@RequestBody Review review, @AuthenticationPrincipal User userPrincipal, @PathVariable("propertyId")
    Long propertyId) {
        User user = userService.getUserByUsername(userPrincipal.getUsername());
        review.setUser(user);
        reviewService.save(review, propertyId);
    }

    @GetMapping("/property/{propertyId}")
    public List<ReviewResponseDTO> getAllByProperty(@PathVariable Long propertyId) {
        return reviewService.getByPropertyId(propertyId);
    }
}
