package com.myfood.ingredients;

import com.myfood.dishes.model.Category;
import com.myfood.ingredients.model.Ingredient;
import com.myfood.ingredients.model.details.Tag;

public class IngredientBuilder {

    public class CategoryBuilder {
        private Category category;

        private CategoryBuilder(Long id) {
            this.category = new Category(id);
        }

        public CategoryBuilder withName(String name) {
            category.setName(name);
            return this;
        }

        public CategoryBuilder withDescription(String description) {
            category.setDescription(description);
            return this;
        }

        public CategoryBuilder withImageId(String imageId) {
            category.setImageId(imageId);
            return this;
        }
    }


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

    public IngredientBuilder withCalories(float calories) {
        ingredient.setCalories(calories);
        return this;
    }

    public IngredientBuilder withProteins(float proteins) {
        ingredient.setProteins(proteins);
        return this;
    }

    public IngredientBuilder withFats(float fats) {
        ingredient.setFats(fats);
        return this;
    }

    public IngredientBuilder withCarbohydrates(float carbohydrates) {
        ingredient.setCarbohydrates(carbohydrates);
        return this;
    }

    public IngredientBuilder withImageId(String imageId) {
        ingredient.setImageId(imageId);
        return this;
    }

    public CategoryBuilder withCategory(Long id) {
        return new CategoryBuilder(id);
    }

    public IngredientBuilder withTag(Long id, String name) {
        ingredient.addTag(new Tag(id, name));
        return this;
    }

    public Ingredient build() {
        return ingredient;
    }

}
