package com.myfood.reconciliation.model.dto;

import lombok.Getter;

/**
 * Created by rakov on 09.08.2019.
 */
public class TagDTO {
    @Getter
    private String name;

    public TagDTO() {
    }

    public TagDTO(String name) {
        this.name = name;
    }
}
