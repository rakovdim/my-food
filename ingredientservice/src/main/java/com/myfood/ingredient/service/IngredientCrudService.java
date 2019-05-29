package com.myfood.ingredient.service;

import com.myfood.ingredient.model.Ingredient;
import com.myfood.ingredient.repository.IngredientRepository;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class IngredientCrudService {
    private IngredientRepository repository;

    @Autowired
    public IngredientCrudService(IngredientRepository repository) {
        this.repository = repository;
    }

    public Ingredient createNewIngredient(Ingredient ingredient) {
        log.debug("Creating new ingredient: " + ingredient);

        return repository.save(ingredient);
    }

    public Ingredient getIngredient(ObjectId id) throws EntityNotFoundException {
        log.debug("Looking for ingredient: " + id);

        return findIngredientEnsure(id);
    }


    private Ingredient findIngredientEnsure(ObjectId id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ingredient: " + id + " not found"));
    }
}
