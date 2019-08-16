package com.myfood.dishes.client;

import com.myfood.dishes.dto.IngredientDTO;
import java.util.List;
import java.util.UUID;

/**
 * Created by rakov on 15.08.2019.
 */
public interface IngredientsServiceClient {
    List<IngredientDTO> getIngredientsByIds(List<UUID> ids);
}
