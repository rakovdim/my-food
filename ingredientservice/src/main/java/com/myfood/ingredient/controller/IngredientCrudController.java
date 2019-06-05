package com.myfood.ingredient.controller;

import com.myfood.ingredient.controller.dto.IngredientDTO;
import com.myfood.ingredient.controller.dto.mapping.IngredientMapper;
import com.myfood.ingredient.model.Ingredient;
import com.myfood.ingredient.service.IngredientCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ingredient-service/ingredients")
public class IngredientCrudController {

    private IngredientCrudService ingredientCrudService;
    private IngredientMapper mapper;

    @Autowired
    public IngredientCrudController(IngredientCrudService ingredientCrudService, IngredientMapper mapper) {
        this.ingredientCrudService = ingredientCrudService;
        this.mapper = mapper;
    }

    @GetMapping("/{ingredient_id}")
    public IngredientDTO getIngredient(@PathVariable Long ingredientId) {
        Ingredient ingredient = ingredientCrudService.getIngredient(ingredientId);
        return mapper.toDTO(ingredient);
    }
}
