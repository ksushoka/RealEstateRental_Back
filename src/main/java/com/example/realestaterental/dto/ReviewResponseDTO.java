package com.example.realestaterental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewResponseDTO {
    private String username;
    private int rating;
    private String comment;
}