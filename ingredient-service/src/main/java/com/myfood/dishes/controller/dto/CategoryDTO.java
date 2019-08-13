package com.myfood.dishes.controller.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryDTO {
    private UUID id;
    private String name;
    private String description;
    private String imageId;
    private int rating;
}
