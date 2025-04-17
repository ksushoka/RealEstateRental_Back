package com.example.realestaterental.service;

import com.example.realestaterental.dto.ReviewResponseDTO;
import com.example.realestaterental.entity.Property;
import com.example.realestaterental.entity.Review;
import com.example.realestaterental.repository.PropertyRepository;
import com.example.realestaterental.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<ReviewResponseDTO> getByPropertyId(Long propertyId) {
        return reviewRepository.findByPropertyId(propertyId)
                .stream()
                .map(review -> new ReviewResponseDTO(
                        review.getUser().getUsername(),
                        review.getRating(),
                        review.getComment()
                ))
                .collect(Collectors.toList());
    }
}
