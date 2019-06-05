package com.myfood.ingredient.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class NutritionValue {
    private float fats;
    private float proteins;
    private float carbohydrate;
}
