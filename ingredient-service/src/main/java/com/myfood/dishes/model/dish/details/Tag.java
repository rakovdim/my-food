package com.myfood.dishes.model.dish.details;

import com.myfood.commons.model.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by rakov on 06.08.2019.
 */
@Entity
public class Tag extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
