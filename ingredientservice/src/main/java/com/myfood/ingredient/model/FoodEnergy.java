package com.myfood.ingredient.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class FoodEnergy {
    private float calories;
}
