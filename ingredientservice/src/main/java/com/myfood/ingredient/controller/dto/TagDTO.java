package com.myfood.ingredient.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TagDTO {
    private Long id;
    @NotBlank
    private String name;

}
