package com.myfood.reconciliation.service.processors.impl;


import com.myfood.reconciliation.model.dto.IngredientDTO;
import com.myfood.reconciliation.model.dto.PlainEntity;
import com.myfood.reconciliation.model.dto.TagDTO;
import com.myfood.reconciliation.service.processors.DataProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by rakov on 13.08.2019.
 */
@Component
public class IngredientDataProcessor implements DataProcessor<IngredientDTO> {
    @Override
    public IngredientDTO convert(PlainEntity plainEntity) {

        String name = plainEntity.getValue("name");
        String desc = plainEntity.getValue("description");
        double proteins = Double.parseDouble(plainEntity.getValue("proteins"));
        double fats = Double.parseDouble(plainEntity.getValue("fats"));
        double calories = Double.parseDouble(plainEntity.getValue("calories"));
        double carbohydrates = Double.parseDouble(plainEntity.getValue("carbohydrates"));
        String tags = plainEntity.getValue("tags");
        String imageId = plainEntity.getValue("imageId");

        IngredientDTO ingredientDTO = new IngredientDTO(name, desc, proteins, carbohydrates, fats, calories, imageId);

        if (!StringUtils.isEmpty(tags)) {
            ingredientDTO.getTags().addAll(getTags(tags));
        }

        return ingredientDTO;
    }


    private Set<TagDTO> getTags(String tags) {
        String[] tagsArray = tags.split(";");
        if (tagsArray.length == 0)
            return Collections.emptySet();

        if (tagsArray.length == 1)
            return Collections.singleton(new TagDTO(tagsArray[0]));

        return Arrays.stream(tagsArray).map(TagDTO::new).collect(Collectors.toSet());
    }

}
