package com.myfood.reconciliation.model.dto;

import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;

/**
 * Created by rakov on 09.08.2019.
 */
@Data
public class DishDTO {
    private String name;
    private String description;
    private String complexity;
    private String visibility;
    private String imageId;
    private String videoId;
    private String authorId;
    private int likes;

    private List<CategoryDTO> categories;
    private List<TagDTO> tags;
    private List<IngredientQuantityDTO> ingredientQuantities;
    private List<List<String>> receiptSteps;

}
