package com.myfood.ingredients.service;

import com.myfood.ingredients.model.Ingredient;

public interface IngredientService {
    Ingredient createNewIngredient(Ingredient ingredient);

    Ingredient getIngredient(Long id);

    Ingredient getIngredientWithTags(Long id);

    Iterable<Ingredient> findAllIngredients(int page, int count);

    void updateIngredient(Long id, Ingredient model);

    void deleteIngredient(Long id);
}
