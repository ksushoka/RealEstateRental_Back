package com.example.realestaterental.controller;

import com.example.realestaterental.entity.Photo;
import com.example.realestaterental.entity.Property;
import com.example.realestaterental.entity.User;
import com.example.realestaterental.entity.type.AmenityType;
import com.example.realestaterental.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/properties")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PropertyController {

    private final PropertyRepository propertyRepository;

    @GetMapping
    public List<Property> getProperties(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String location
    ) {
        return propertyRepository.findWithFilters(minPrice, maxPrice, location);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Property> addProperty(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam Double pricePerNight,
            @RequestParam String location,
            @RequestParam List<AmenityType> amenityTypes,
            @RequestParam("photos") MultipartFile[] photos,
            @AuthenticationPrincipal User user
            ) throws IOException {

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

        propertyRepository.save(property);
        return ResponseEntity.ok(property);
    }

    @GetMapping("/photos/{filename}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String filename) throws IOException {
        // Убедитесь, что путь к папке с фотографиями указан правильно
        Path filePath = Paths.get("photos").resolve(filename);
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/photos")
    public ResponseEntity<List<String>> getPhotosByProperty(@PathVariable Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));
        List<String> photoFileNames = property.getPhotos().stream()
                .map(Photo::getFileName)
                .collect(Collectors.toList());
        return ResponseEntity.ok(photoFileNames);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyDetails(@PathVariable Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));
        return ResponseEntity.ok(property);
    }
}

