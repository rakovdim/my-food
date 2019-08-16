package com.myfood.reconciliation.model.dto;

import lombok.Data;

/**
 * Created by rakov on 09.08.2019.
 */
@Data
public class CategoryDTO {
    private String name;
    private String description;
    private int rating;
    private String imageId;


    public CategoryDTO(String name, String description, int rating, String imageId) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.imageId = imageId;
    }


    public CategoryDTO(String name) {
        this.name = name;
    }


    public CategoryDTO() {
    }
}
