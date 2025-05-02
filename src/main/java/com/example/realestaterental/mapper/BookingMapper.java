package com.example.realestaterental.mapper;

import com.example.realestaterental.dto.BookingDTO;
import com.example.realestaterental.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(source = "guest.id",    target = "guestId")
    @Mapping(source = "property.id", target = "propertyId")
    BookingDTO toDto(Booking b);

    List<BookingDTO> toDtoList(List<Booking> bookings);
}
