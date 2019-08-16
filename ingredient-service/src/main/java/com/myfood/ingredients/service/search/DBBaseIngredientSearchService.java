package com.myfood.ingredients.service.search;

import com.myfood.ingredients.model.Ingredient;
import com.myfood.ingredients.repository.IngredientRepository;
import com.myfood.ingredients.service.IngredientSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DBBaseIngredientSearchService implements IngredientSearchService {
    private IngredientRepository repository;

    @Autowired
    public DBBaseIngredientSearchService(IngredientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Ingredient> findByTags(Set<String> tags) {
        return null;
    }

    @Override
    public List<Ingredient> findByName(String name) {
        return null;
    }
}
