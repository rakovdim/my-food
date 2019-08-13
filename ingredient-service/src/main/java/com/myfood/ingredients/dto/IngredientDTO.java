package com.myfood.ingredients.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IngredientDTO {
    private UUID id;
    @NotBlank
    @Size(min = 1, max = 20)
    private String name;
    //todo check
    @Max(1000)
    private String description;
    @NotNull
    @Max(100)
    private Double fats;
    @NotNull
    @Max(100)
    private Double proteins;
    @NotNull
    @Max(100)
    private Double carbohydrates;
    @NotNull
    private Double calories;
    @Valid
    private List<TagDTO> tags;
    @NotNull
    private String imageId;
}
