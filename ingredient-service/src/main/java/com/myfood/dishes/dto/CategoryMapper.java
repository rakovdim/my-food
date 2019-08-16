package com.myfood.dishes.dto;

import com.myfood.dishes.model.dish.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    public CategoryDTO categoryDTOfromDO(Category category);
}
