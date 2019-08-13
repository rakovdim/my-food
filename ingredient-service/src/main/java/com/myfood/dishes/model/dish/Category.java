package com.myfood.dishes.model.dish;

import com.myfood.commons.model.entities.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

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
    private String description;
    private String imageId;
    private int rating;

    public Category() {
    }

    public Category(UUID id) {
        super(id);
    }

    public Category(UUID id, String name, int rating) {
        super(id);
        this.name = name;
        this.rating = rating;
    }
}
