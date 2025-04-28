package com.example.realestaterental.dto;

import com.example.realestaterental.entity.type.AmenityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDTO {
    private Long id;
    private String title;
    private String description;
    private Double pricePerNight;
    private String location;
    private Set<AmenityType> amenityTypes;
    private Double averageRating;

    @Setter // Явно указываем сеттер
    private Long hostId;

    private List<String> photos;
}
