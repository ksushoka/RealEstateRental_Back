package com.example.realestaterental.service;

import com.example.realestaterental.entity.Property;
import com.example.realestaterental.entity.Review;
import com.example.realestaterental.repository.PropertyRepository;
import com.example.realestaterental.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final PropertyRepository propertyRepository;
    public void save(Review review, Long propertyId) {
        Property property = propertyRepository.findById(propertyId).get();
        review.setProperty(property);
        reviewRepository.save(review);
    }
}
