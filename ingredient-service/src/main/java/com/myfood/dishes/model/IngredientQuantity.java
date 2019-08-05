package com.myfood.dishes.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class IngredientQuantity {
    @Column(updatable = false)
    private Long dishId;
    private Long ingredientId;
    private Quantity quantity;
    private double value;

    public IngredientQuantity(Long ingredientId, Quantity quantity, double value) {
        this.ingredientId = ingredientId;
        this.quantity = quantity;
        this.value = value;
    }

    public Long getDishId() {
        return dishId;
    }

    void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public double getValue() {
        return value;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public void setValue(double value) {
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientQuantity that = (IngredientQuantity) o;
        return Objects.equals(dishId, that.dishId) &&
                Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, ingredientId);
    }
}
