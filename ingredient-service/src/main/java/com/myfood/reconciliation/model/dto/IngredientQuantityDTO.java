package com.myfood.reconciliation.model.dto;

import java.util.UUID;
import lombok.Data;

/**
 * Created by rakov on 13.08.2019.
 */
@Data
public class IngredientQuantityDTO {
    private UUID ingredientId;
    private String ingredientName;
    private double weight;
    private String measureType;
}
