package com.myfood.dishes.model.dish;

import com.myfood.commons.model.entities.AuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "dish_principles")
@Data
public class Principle extends AuditedEntity implements OrderedItem {

    private String text;
    private int ordering;

    public Principle(Long id, String text) {
        super(id);
        this.text = text;
    }

    public Principle() {
    }
}
