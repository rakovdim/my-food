package com.myfood.dishes.dto.mappers;

import com.myfood.dishes.dto.CategoryDTO;
import com.myfood.dishes.model.dish.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    public CategoryDTO mapCategoryDTOfromDO(Category category);

    public Category mapCategoryDOFromDTO(CategoryDTO categoryDTO);
}
