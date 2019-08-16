package com.myfood.ingredients.dto.mapping;

import com.myfood.ingredients.dto.IngredientDTO;
import com.myfood.ingredients.dto.TagDTO;
import com.myfood.ingredients.model.Ingredient;
import com.myfood.ingredients.model.details.Tag;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class IngredientMapper {
    public IngredientDTO toDTO(Ingredient ingredient) {
        IngredientDTO ingredientDTO = new IngredientDTO();

        ingredientDTO.setId(ingredient.getId());
        ingredientDTO.setName(ingredient.getName());
        ingredientDTO.setDescription(ingredient.getDescription());
        ingredientDTO.setFats(ingredient.getFats());
        ingredientDTO.setProteins(ingredient.getProteins());
        ingredientDTO.setCarbohydrates(ingredient.getCarbohydrates());
        ingredientDTO.setCalories(ingredient.getCalories());
        ingredientDTO.setTags(ingredient.getTags().stream().map(this::toTagDTO).collect(Collectors.toList()));

        return ingredientDTO;
    }

    public Ingredient toDO(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();

        ingredient.setName(ingredientDTO.getName());
        ingredient.setDescription(ingredientDTO.getDescription());
        ingredient.setFats(ingredientDTO.getFats());
        ingredient.setProteins(ingredientDTO.getProteins());
        ingredient.setCarbohydrates(ingredientDTO.getCarbohydrates());
        ingredient.setCalories(ingredientDTO.getCalories());
        ingredient.addTags(ingredientDTO.getTags().stream().map(this::toTagDo).collect(Collectors.toList()));

        return ingredient;
    }

    private Tag toTagDo(TagDTO tagDTO) {
        return new Tag(tagDTO.getId(), tagDTO.getName());
    }

    private TagDTO toTagDTO(Tag tag) {
        return new TagDTO(tag.getId(), tag.getName());
    }
}
