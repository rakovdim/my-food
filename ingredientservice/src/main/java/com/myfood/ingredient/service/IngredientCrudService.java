package com.myfood.ingredient.service;

import com.myfood.ingredient.model.Ingredient;

public interface IngredientCrudService {
    Ingredient createNewIngredient(Ingredient ingredient);

    Ingredient getIngredient(Long id);

    Ingredient getIngredientWithTags(Long id);

    Iterable<Ingredient> findAllIngredients(int page, int count);

    void updateIngredient(Long id, Ingredient model);

    void deleteIngredient(Long id);
}
