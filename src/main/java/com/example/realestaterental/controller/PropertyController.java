package com.example.realestaterental.controller;

import com.example.realestaterental.dto.PropertyDTO;
import com.example.realestaterental.entity.Property;
import com.example.realestaterental.entity.User;
import com.example.realestaterental.entity.type.AmenityType;
import com.example.realestaterental.mapper.PropertyMapper;
import com.example.realestaterental.service.PropertyService;
import com.example.realestaterental.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/properties")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PropertyController {
    private final UserService userService;
    private final PropertyService propertyService;
    private final PropertyMapper propertyMapper;
//    @GetMapping
//    public List<Property> getProperties(
//            @RequestParam(required = false) Double minPrice,
//            @RequestParam(required = false) Double maxPrice,
//            @RequestParam(required = false) String location
//    ) {
//        return propertyService.getProperties(minPrice, maxPrice, location);
//    }
    @GetMapping
    public List<PropertyDTO> getProperties(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String location
    ) {
        List<Property> props = propertyService.getProperties(minPrice, maxPrice, location);
        return propertyMapper.toDtoList(props);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Property> addProperty(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam Double pricePerNight,
            @RequestParam String location,
            @RequestParam Set<AmenityType> amenityTypes,
            @RequestParam("photos") MultipartFile[] photos,
            @AuthenticationPrincipal User userPrincipal
    ) throws IOException {
        User user = userService.getUserByUsername(userPrincipal.getUsername());
//
        Property property = propertyService.addProperty(title, description, pricePerNight, location, amenityTypes, photos, user);
        return ResponseEntity.ok(property);
    }
    @GetMapping("/rating/{id}")
    public ResponseEntity<Double> findAverageReviewForProperty(@PathVariable Long id){
        return ResponseEntity.ok(propertyService.findAverageReviewForProperty(id));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Property> getPropertyDetails(@PathVariable Long id) {
//        Property property = propertyService.getPropertyDetails(id);
//        return ResponseEntity.ok(property);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyDetails(@PathVariable Long id) {
        Property property = propertyService.getPropertyDetails(id);
        return ResponseEntity.ok(propertyMapper.toDto(property));
    }

    @GetMapping("/{id}/photos")
    public ResponseEntity<List<String>> getPhotosByProperty(@PathVariable Long id) {
        List<String> photoFileNames = propertyService.getPhotosByProperty(id);
        return ResponseEntity.ok(photoFileNames);
    }

    @GetMapping("/photos/{filename}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String filename) throws IOException {
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
}
