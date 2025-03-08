package com.example.realestaterental.entity;

import com.example.realestaterental.entity.type.AmenityType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private String location;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Photo> photos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;

    @ElementCollection
    private List<AmenityType> amenityTypes = new ArrayList<>();

//    public void addAmenityType(List<AmenityType> amenityType){
//        this.amenityTypes.addAll(amenityType);
//    }
//    @OneToMany(mappedBy = "property")
//    private List<Booking> bookings;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "property_amenity",
//            joinColumns = @JoinColumn(name = "property_id"),
//            inverseJoinColumns = @JoinColumn(name = "amenity_id")
//    )
//    private List<Amenity> amenities = new ArrayList<>();

//    @OneToMany(mappedBy = "property")
//    private List<Photo> photos;
//}
}