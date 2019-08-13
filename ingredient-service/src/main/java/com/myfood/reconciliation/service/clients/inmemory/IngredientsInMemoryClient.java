package com.myfood.reconciliation.service.clients.inmemory;

import com.myfood.ingredients.model.Ingredient;
import com.myfood.ingredients.service.IngredientService;
import com.myfood.reconciliation.model.dto.IngredientDTO;
import com.myfood.reconciliation.service.clients.ClientIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class IngredientsInMemoryClient implements ClientIntegration<IngredientDTO> {

    private IngredientService ingredientService;
    private ReconcDataMapper dataMapper;

    @Autowired
    public IngredientsInMemoryClient(IngredientService ingredientService, ReconcDataMapper dataMapper) {
        this.ingredientService = ingredientService;
        this.dataMapper = dataMapper;
    }

    @Override
    public List<UUID> uploadAll(List<IngredientDTO> entities) {
        List<Ingredient> ingredients = entities.stream().map(dataMapper::ingredientDTOtoDO).collect(Collectors.toList());

        return ingredientService.saveAll(ingredients);
    }

    @Override
    public List<IngredientDTO> findByNames(List<String> names) {
        List<IngredientDTO> res=  ingredientService.findByNames(names, 0, Integer.MAX_VALUE).stream().map(dataMapper::ingredientDOtoDTO).collect(Collectors.toList());

        return res;
    }

    @Override
    public List<IngredientDTO> findByIds(List<UUID> names) {
        return null;
    }
}
