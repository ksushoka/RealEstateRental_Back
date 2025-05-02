package com.example.realestaterental.service;

import com.example.realestaterental.entity.Photo;
import com.example.realestaterental.entity.Property;
import com.example.realestaterental.entity.Review;
import com.example.realestaterental.entity.User;
import com.example.realestaterental.entity.type.AmenityType;
import com.example.realestaterental.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public List<Property> getProperties(Double minPrice, Double maxPrice, String location) {
        return propertyRepository.findWithFilters(minPrice, maxPrice, location);
    }

    public Property addProperty(String title,
                                String description,
                                Double pricePerNight,
                                String location,
                                Set<AmenityType> amenityTypes,
                                MultipartFile[] photos,
                                User user) throws IOException {

        Property property = new Property();
        property.setTitle(title);
        property.setDescription(description);
        property.setPricePerNight(pricePerNight);
        property.setLocation(location);
        property.setAmenityTypes(amenityTypes);
        property.setHost(user);

        List<Photo> photoList = new ArrayList<>();
        for (MultipartFile photo : photos) {
            String fileName = UUID.randomUUID() + "_" + photo.getOriginalFilename();
            Path filePath = Paths.get("photos").resolve(fileName);
            Files.createDirectories(filePath.getParent());
            Files.copy(photo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Photo photoEntity = new Photo();
            photoEntity.setFileName(fileName);
            photoEntity.setProperty(property);
            photoList.add(photoEntity);
        }
        property.setPhotos(photoList);

        return propertyRepository.save(property);
    }

    public Property getPropertyDetails(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));
    }
    public double findAverageReviewForProperty(Long id) {
        Property property = propertyRepository.findById(id).get();
        List<Review> reviews = property.getReviews();
        double sum = 0;
        for (Review review : reviews) {
           double rating = review.getRating();
            sum += rating;
        }
        return sum / reviews.size();
    }

    public List<String> getPhotosByProperty(Long id) {
        Property property = getPropertyDetails(id);
        return property.getPhotos().stream()
                .map(Photo::getFileName)
                .collect(Collectors.toList());
    }
}
