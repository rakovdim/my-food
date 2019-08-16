package com.myfood.reconciliation.service.clients;

import com.myfood.dishes.model.dish.Category;
import com.myfood.ingredients.model.Ingredient;
import com.myfood.ingredients.model.details.Tag;
import com.myfood.reconciliation.model.dto.CategoryDTO;
import com.myfood.reconciliation.model.dto.IngredientDTO;
import com.myfood.reconciliation.model.dto.TagDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReconcDataMapper {
    @Mapping(ignore = true, target = "tags")
    public IngredientDTO ingredientDOtoDTO(Ingredient ingredient);

    public Ingredient ingredientDTOtoDO(IngredientDTO ingredient);

    public Tag tagDTOtoDO(TagDTO tag);

    public TagDTO tagDOtoDTO(Tag tag);

    public Category categoryDTOtoDO(CategoryDTO categoryDTO);

    public CategoryDTO categoryDOtoDTO(Category category);
}
