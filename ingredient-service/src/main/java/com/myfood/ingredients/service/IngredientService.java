package com.myfood.ingredients.service;

import com.myfood.ingredients.model.Ingredient;

import java.util.List;
import java.util.UUID;

public interface IngredientService {
    Ingredient createNewIngredient(Ingredient ingredient);

    Ingredient getIngredient(Long id);

    Ingredient getIngredientWithTags(Long id);

    Iterable<Ingredient> findAllIngredients(int page, int count);

    void updateIngredient(Long id, Ingredient model);

    void deleteIngredient(Long id);

    List<UUID> saveAll(List<Ingredient> ingredients);

    List<Ingredient> findByNames(List<String> name, int page, int count);
}
