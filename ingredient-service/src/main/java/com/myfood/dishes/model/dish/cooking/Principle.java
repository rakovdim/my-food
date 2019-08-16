package com.myfood.dishes.model.dish.cooking;

import com.myfood.commons.model.entities.AuditedEntity;
import com.myfood.dishes.model.dish.OrderedItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "dish_principles")
@Data
public class Principle extends AuditedEntity implements OrderedItem {

    private String content;
    private int ordering;

    public Principle(UUID id, String content) {
        super(id);
        this.content = content;
    }

    public Principle() {
    }
}
