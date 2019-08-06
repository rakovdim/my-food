package com.myfood.dishes.model.ingredient;

/**
 * Created by rakov on 02.08.2019.
 */
public class Ingredient
{
    private NutritionValue value;


    public Ingredient(NutritionValue value)
    {
        this.value = value;
    }


    public NutritionValue getNutrition()
    {
        return value;
    }


    public void setValue(NutritionValue value)
    {
        this.value = value;
    }
}
