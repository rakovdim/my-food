package com.myfood.dishes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myfood.dishes.model.dish.cooking.Complexity;
import com.myfood.dishes.model.dish.cooking.ScalingStrategy;
import com.myfood.dishes.model.dish.system.Status;
import com.myfood.dishes.model.dish.system.Visibility;
import com.myfood.dishes.model.ingredient.VolumeMeasure;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class    DishDTO {
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class IngredientQuantityDTO {
        @NotNull
        private UUID ingredientId;
        @NotNull
        private VolumeMeasure volumeMeasure;
        @NotNull
        @Positive
        private double weight;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PrincipleDTO {
        private UUID id;
        private String text;

    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ReceiptStepDTO {
        private String text;
        private String imageId;
        private String videoId;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ReceiptDTO {
        private UUID id;
        private Complexity complexity;
        private List<ReceiptStepDTO> steps;
    }

    private UUID id;
    @NotNull
    private String name;
    private String description;
    private List<CategoryDTO> categories;
    private List<IngredientQuantityDTO> ingredients;
    private Set<String> tags;
    private List<PrincipleDTO> principles;
    private Status status;
    private Visibility visibility;
    private UUID authorId;
    private ScalingStrategy scalingStrategy;
    private String imageId;
    private String videoId;


}
