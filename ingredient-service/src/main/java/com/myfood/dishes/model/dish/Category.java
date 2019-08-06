package com.myfood.dishes.model.dish;

import com.myfood.commons.model.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by rakov on 06.08.2019.
 */
@Entity
@Table(name = "categories")
public class Category extends AbstractEntity {
    @Column(nullable = false)
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category(Long id, String name) {
        super(id);
        this.name = name;
    }
//
//    public Map<Long, Dish> getDishes() {
//        return Collections.unmodifiableMap(dishes);
//    }
//
//    void addDish(Dish dish) {
//        this.dishes.put(dish.getId(), dish);
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
