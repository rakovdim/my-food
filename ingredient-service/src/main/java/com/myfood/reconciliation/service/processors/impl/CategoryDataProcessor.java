package com.myfood.reconciliation.service.processors.impl;

import com.myfood.reconciliation.model.dto.CategoryDTO;
import com.myfood.reconciliation.model.dto.PlainEntity;
import com.myfood.reconciliation.service.processors.DataProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by rakov on 13.08.2019.
 */
@Component
public class CategoryDataProcessor implements DataProcessor<CategoryDTO> {
    @Override
    public CategoryDTO convert(PlainEntity plainEntity) {

        String name = plainEntity.getValue("name");
        String imageId = plainEntity.getValue("imageId");
        String description = plainEntity.getValue("description");
        int rating = Integer.parseInt(plainEntity.getValue("rating"));

        return new CategoryDTO(name, description, rating, imageId);
    }

}
