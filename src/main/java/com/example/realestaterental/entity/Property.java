package com.example.realestaterental.entity;

import com.example.realestaterental.entity.type.AmenityType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @JsonBackReference
    private User host;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<AmenityType> amenityTypes = new HashSet<>();

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Review> reviews;
}