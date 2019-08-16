package com.myfood.ingredients.controller;

import com.myfood.ingredients.dto.IngredientDTO;
import com.myfood.ingredients.dto.mapping.IngredientMapper;
import com.myfood.ingredients.service.IngredientSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.myfood.ingredients.constants.IngredientConsts.*;

@RestController
@RequestMapping(INGREDIENT_SERVICE_V1_URL + AND + SEARCH_PATH)
public class IngredientSearchController {

    private IngredientSearchService searchService;
    private IngredientMapper mapper;

    @Autowired
    public IngredientSearchController(IngredientSearchService searchService, IngredientMapper mapper) {
        this.searchService = searchService;
        this.mapper = mapper;
    }

    @PostMapping("/findByTags}")
    public List<IngredientDTO> findByTags(Set<String> tags) {
        return searchService.findByTags(tags).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping
    public List<IngredientDTO> findByName(@RequestParam String name) {
        return searchService.findByName(name).stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}
