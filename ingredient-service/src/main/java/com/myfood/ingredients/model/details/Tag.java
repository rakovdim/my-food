package com.myfood.ingredients.model.details;

import com.myfood.commons.model.entities.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tags")
public class Tag extends AbstractEntity {

    @Column(updatable = false, unique = true, nullable = false)
    private String name;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public Tag(UUID id, String name) {
        super(id);
        this.name = name;
    }
}
