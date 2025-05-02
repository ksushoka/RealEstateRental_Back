package com.example.realestaterental.repository;

import com.example.realestaterental.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    @Query("SELECT p FROM Property p " +
            "WHERE (:minPrice IS NULL OR p.pricePerNight >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.pricePerNight <= :maxPrice) " +
            "AND (:location IS NULL OR p.location LIKE %:location%) ")
//            +
//            "AND (:amenities IS NULL OR EXISTS (SELECT a FROM p.amenities a WHERE a.name IN :amenities))")
    List<Property> findWithFilters(@Param("minPrice") Double minPrice,
                                   @Param("maxPrice") Double maxPrice,
                                   @Param("location") String location);
//                                   @Param("amenities") List<String> amenities
//    );

    List<Property> findByHostId(Integer id);

    @Query("SELECT p FROM Property p LEFT JOIN FETCH p.photos WHERE p.id = :id")
    Optional<Property> findByIdWithPhotos(@Param("id") Long id);

    @Query("SELECT p FROM Property p LEFT JOIN FETCH p.reviews WHERE p.id = :id")
    Optional<Property> findByIdWithReviews(@Param("id") Long id);
}


