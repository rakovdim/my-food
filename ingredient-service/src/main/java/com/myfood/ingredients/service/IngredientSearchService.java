package com.myfood.ingredients.service;

import com.myfood.ingredients.model.Ingredient;

import java.util.List;
import java.util.Set;

public interface IngredientSearchService {
    List<Ingredient> findByTags(Set<String> tags);

    List<Ingredient> findByName(String name);
}
