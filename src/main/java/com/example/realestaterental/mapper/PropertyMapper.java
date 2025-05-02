package com.example.realestaterental.mapper;

import com.example.realestaterental.dto.PropertyDTO;
import com.example.realestaterental.entity.Photo;
import com.example.realestaterental.entity.Property;
import com.example.realestaterental.service.PropertyService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        uses = PropertyService.class             // ← добавили
)
public interface PropertyMapper {
    @Mapping(target = "hostId", expression = "java(getHostId(property))")
    @Mapping(target = "photos", expression = "java(mapPhotos(property.getPhotos()))")
//    @Mapping(target = "averageRating", expression = "java(propertyService.findAverageReviewForProperty(property.getId()))")
    PropertyDTO toDto(Property property);

    default List<String> mapPhotos(List<Photo> photos) {
        if (photos == null) return Collections.emptyList();
        return photos.stream()
                .map(Photo::getFileName)
                .collect(Collectors.toList());
    }

    default Long getHostId(Property property) {
        if (property == null || property.getHost() == null) return null;
        return property.getHost().getId().longValue(); // Конвертируем Integer в Long
    }

    List<PropertyDTO> toDtoList(List<Property> properties);
}
