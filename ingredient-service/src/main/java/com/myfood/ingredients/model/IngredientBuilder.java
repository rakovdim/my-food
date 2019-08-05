package com.myfood.ingredients.model;

import com.myfood.ingredients.model.details.Tag;
import com.myfood.ingredients.model.details.VolumeMeasure;

public class IngredientBuilder {
    private Ingredient ingredient;

    public static IngredientBuilder newIngredient(Long id) {
        return new IngredientBuilder(id);
    }

    private IngredientBuilder(Long id) {
        this.ingredient = new Ingredient(id);
    }

    public IngredientBuilder withName(String name) {
        ingredient.setName(name);
        return this;
    }

    public IngredientBuilder withDescription(String description) {
        ingredient.setDescription(description);
        return this;
    }

    public IngredientBuilder withCalories(double calories) {
        ingredient.setCalories(calories);
        return this;
    }

    public IngredientBuilder withProteins(double proteins) {
        ingredient.setProteins(proteins);
        return this;
    }

    public IngredientBuilder withFats(double fats) {
        ingredient.setFats(fats);
        return this;
    }

    public IngredientBuilder withCarbohydrates(double carbohydrates) {
        ingredient.setCarbohydrates(carbohydrates);
        return this;
    }

    public IngredientBuilder withVolumeMeasure(VolumeMeasure measure) {
        ingredient.setVolumeMeasure(measure);
        return this;
    }

    public IngredientBuilder withImageId(String imageId) {
        ingredient.setImageId(imageId);
        return this;
    }

    public IngredientBuilder withTag(Tag tag) {
        ingredient.addTag(tag);
        return this;
    }

    public Ingredient build() {
        return ingredient;
    }

}
