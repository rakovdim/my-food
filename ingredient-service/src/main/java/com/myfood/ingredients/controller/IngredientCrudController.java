package com.myfood.ingredients.controller;

import com.myfood.ingredients.dto.IngredientDTO;
import com.myfood.ingredients.dto.mapping.IngredientMapper;
import com.myfood.ingredients.model.Ingredient;
import com.myfood.ingredients.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.myfood.ingredients.constants.IngredientConsts.*;

@RestController
@RequestMapping(INGREDIENT_SERVICE_V1_URL + AND + INGREDIENTS_PATH)
public class IngredientCrudController {

    private IngredientService ingredientService;
    private IngredientMapper ingredientMapper;

    @Autowired
    public IngredientCrudController(IngredientService ingredientService, IngredientMapper mapper) {
        this.ingredientService = ingredientService;
        this.ingredientMapper = mapper;
    }

    @GetMapping("/{ingredient_id}")
    public IngredientDTO getIngredient(@PathVariable Long ingredientId) {
        Ingredient ingredient = ingredientService.getIngredient(ingredientId);

        return ingredientMapper.toDTO(ingredient);
    }

    @PostMapping
    public IngredientDTO createIngredient(@Valid @RequestBody IngredientDTO ingredientDTO) {
        Ingredient model = ingredientMapper.toDO(ingredientDTO);

        Ingredient stored = ingredientService.createNewIngredient(model);

        return ingredientMapper.toDTO(stored);
    }

    @PutMapping("/{ingredient_id}")
    public void updateIngredient(@PathVariable Long ingredientId, @Valid @RequestBody IngredientDTO ingredientDTO) {
        Ingredient model = ingredientMapper.toDO(ingredientDTO);

        ingredientService.updateIngredient(ingredientId, model);
    }

    @DeleteMapping("/{ingredient_id}")
    public void deleteIngredient(@PathVariable Long ingredientId) {
        ingredientService.deleteIngredient(ingredientId);
    }
}
