package com.myfood.ingredients.model.details;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class NutritionInfo {
    @Column(nullable = false)
    private double fats;
    @Column(nullable = false)
    private double proteins;
    @Column(nullable = false)
    private double carbohydrates;
}
