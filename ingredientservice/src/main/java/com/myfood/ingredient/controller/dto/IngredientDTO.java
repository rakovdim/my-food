package com.myfood.ingredient.controller.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class IngredientDTO {
    private Long id;
    @NotBlank
    @Size(min = 1, max = 20)
    private String name;
    private String desc;
    @NotNull
    @Max(100)
    private Float fats;
    @NotNull
    @Max(100)
    private Float proteins;
    @NotNull
    @Max(100)
    private Float carbohydrate;
    @NotNull
    private Float calories;
    @Valid
    private List<TagDTO> tags;

}
