package com.myfood.dishes.dto.mappers;

import com.myfood.dishes.dto.DishDTO;
import com.myfood.dishes.model.dish.Dish;
import com.myfood.dishes.model.dish.cooking.Principle;
import com.myfood.dishes.model.dish.social.DishTag;
import com.myfood.dishes.model.ingredient.IngredientQuantity;
import com.myfood.dishes.service.crud.DishModelFactory;
import com.myfood.ingredients.dto.TagDTO;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {

    private CategoryMapper categoryMapper;
    private DishModelFactory dishModelFactory;

    public DishMapper(CategoryMapper categoryMapper, DishModelFactory dishModelFactory) {
        this.categoryMapper = categoryMapper;
        this.dishModelFactory = dishModelFactory;
    }

    public DishDTO mapIdNameImageOnly(Dish dish) {
        DishDTO dishDTO = new DishDTO();
        dishDTO.setId(dish.getId());
        dishDTO.setName(dish.getName());
        dishDTO.setImageId(dish.getImageId());
        return dishDTO;
    }

    public DishDTO mapFlatAndSystemInfo(Dish dish) {
        DishDTO dishDTO = mapIdNameImageOnly(dish);
        return null;
    }

    public Dish mapDishDTOtoDOFull(DishDTO dishDTO) {
        Dish dish = dishModelFactory.createEmptyDish();

        dish.setName(dishDTO.getName());
        dish.setDescription(dishDTO.getDescription());
        dish.setStatus(dishDTO.getStatus());
        dish.setVisibility(dishDTO.getVisibility());
        dish.setScalingStrategy(dishDTO.getScalingStrategy());
        dish.setAuthorId(dishDTO.getAuthorId());
        dish.setVideoId(dishDTO.getVideoId());
        dish.setImageId(dishDTO.getImageId());

        dishDTO.getCategories().stream().map(categoryMapper::mapCategoryDOFromDTO).forEach(dish::addCategory);
        dishDTO.getIngredients().stream().map(this::mapIngredientQuantityDTO).forEach(dish::addIngredient);
        dishDTO.getPrinciples().stream().map(this::mapPrincipleDTO).forEach(dish::addPrinciple);

        return dish;
    }

    private IngredientQuantity mapIngredientQuantityDTO(DishDTO.IngredientQuantityDTO ingredientDTO) {
        return new IngredientQuantity(ingredientDTO.getIngredientId(), ingredientDTO.getVolumeMeasure(), ingredientDTO.getWeight());
    }

    private Principle mapPrincipleDTO(DishDTO.PrincipleDTO principleDTO) {
        return dishModelFactory.createPrinciple(principleDTO.getId(), principleDTO.getText());
    }


    private DishTag mapTagDTOtoDO(TagDTO tagDTO) {
        return dishModelFactory.createNewTag(tagDTO.getId(), tagDTO.getName());
    }

    public DishDTO mapDishDOtoDTOFull(Dish dishDTO) {
        return null;
    }
}
