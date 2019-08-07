package com.myfood.dishes.model.dish;

import com.myfood.commons.model.entities.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by rakov on 06.08.2019.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "categories")
@Data
public class Category extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String name;
    private String imageId;

    public Category() {
    }

    public Category(Long id) {
        super(id);
    }

    public Category(Long id, String name) {
        super(id);
        this.name = name;
    }
}
