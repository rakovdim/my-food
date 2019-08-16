package com.myfood.dishes.dto;

import java.util.UUID;
import lombok.Data;

/**
 * Created by rakov on 15.08.2019.
 */
@Data
public class IngredientDTO {
    private UUID id;
    private String name;
    private String description;
    private NutritionInfoDTO nutritionInfo;
}
