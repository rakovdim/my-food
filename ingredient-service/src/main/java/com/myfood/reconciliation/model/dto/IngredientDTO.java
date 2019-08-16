package com.myfood.reconciliation.model.dto;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rakov on 09.08.2019.
 */
@Data
@ToString
public class IngredientDTO {
    private String name;
    private String description;
    private double proteins;
    private double carbohydrates;
    private double fats;
    private double calories;
    private List<TagDTO> tags = new ArrayList<>();
    private String imageId;

    public IngredientDTO() {
    }

    public IngredientDTO(
            String name, String description, double proteins, double carbohydrates, double fats, double calories, String imageId) {
        this.name = name;
        this.description = description;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
        this.calories = calories;
        this.imageId = imageId;
    }

}
