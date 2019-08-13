package com.myfood.reconciliation.service.processors.impl;


import com.myfood.reconciliation.model.dto.DishDTO;
import com.myfood.reconciliation.model.dto.IngredientDTO;
import com.myfood.reconciliation.model.dto.PlainEntity;
import com.myfood.reconciliation.service.clients.ClientIntegration;
import com.myfood.reconciliation.service.processors.DataProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * Created by rakov on 13.08.2019.
 */
@Component
public class DishDataProcessor implements DataProcessor<DishDTO> {
    private ClientIntegration<IngredientDTO> ingredientIntegration;

    @Override

    public DishDTO convert(PlainEntity plainEntity) {

        String name = plainEntity.getValue("name");
        String description = plainEntity.getValue("description");
        String visibility = plainEntity.getValue("visibility");
        String complexity = plainEntity.getValue("complexity");
        UUID authorId = UUID.fromString(plainEntity.getValue("authorid"));
        String imageId = plainEntity.getValue("imageid");
        String videoId = plainEntity.getValue("videoid");

        return new DishDTO();
    }
}
