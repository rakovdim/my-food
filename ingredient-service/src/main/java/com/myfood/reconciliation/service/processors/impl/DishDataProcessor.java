package com.myfood.reconciliation.service.processors.impl;

import com.myfood.dishes.model.dish.Dish;
import com.myfood.reconciliation.model.dto.CategoryDTO;
import com.myfood.reconciliation.model.dto.DishDTO;
import com.myfood.reconciliation.model.dto.IngredientDTO;
import com.myfood.reconciliation.model.dto.PlainEntity;
import com.myfood.reconciliation.model.dto.TagDTO;
import com.myfood.reconciliation.service.clients.ClientIntegration;
import com.myfood.reconciliation.service.processors.DataProcessor;
import com.myfood.reconciliation.utils.StringDataParser;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * Created by rakov on 13.08.2019.
 */
@Component
public class DishDataProcessor implements DataProcessor<DishDTO> {
    private StringDataParser dataParser;


    @Autowired
    public DishDataProcessor(StringDataParser dataParser) {
        this.dataParser = dataParser;
    }


    @Override
    public DishDTO convert(PlainEntity plainEntity) {

        String name = plainEntity.getValue("name");
        String description = plainEntity.getValue("description");
        String visibility = plainEntity.getValue("visibility");
        String complexity = plainEntity.getValue("complexity");
        String authorId = plainEntity.getValue("authorid");
        String imageId = plainEntity.getValue("imageid");
        String videoId = plainEntity.getValue("videoid");
        String tags = plainEntity.getValue("tags");
        String categories = plainEntity.getValue("categories");
        String ingredients = plainEntity.getValue("ingredients");

        DishDTO dishDTO = new DishDTO();
        dishDTO.setName(name);
        dishDTO.setDescription(description);
        dishDTO.setVisibility(visibility);
        dishDTO.setComplexity(complexity);
        dishDTO.setAuthorId(authorId);
        dishDTO.setImageId(imageId);
        dishDTO.setVisibility(videoId);
        dishDTO.setTags(dataParser.parseTags(tags));
        dishDTO.setTags(dataParser.parseBulletsString(categories, TagDTO::new));
        dishDTO.setCategories(dataParser.parseBulletsString(categories, CategoryDTO::new));
        dishDTO.setIngredientQuantities(dataParser.parseBulletsString(ingredients, dataParser::parseIngredientQuantity));

        return dishDTO;
    }


}
