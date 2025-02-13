package com.example.realestaterental.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Double pricePerNight;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Photo> photos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;
}

//    @OneToMany(mappedBy = "property")
//    private List<Booking> bookings;

//    @ManyToMany
//    @JoinTable(
//            name = "property_amenity",
//            joinColumns = @JoinColumn(name = "property_id"),
//            inverseJoinColumns = @JoinColumn(name = "amenity_id")
//    )
//    private List<Amenity> amenities;

//    @OneToMany(mappedBy = "property")
//    private List<Photo> photos;
//}
