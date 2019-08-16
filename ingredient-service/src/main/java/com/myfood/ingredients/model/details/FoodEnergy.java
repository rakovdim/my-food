package com.myfood.ingredients.model.details;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class FoodEnergy {
    @Column(nullable = false)
    private double calories;
}
