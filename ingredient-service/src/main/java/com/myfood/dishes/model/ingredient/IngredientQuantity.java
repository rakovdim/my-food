package com.myfood.dishes.model.ingredient;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

/**
 * Created by rakov on 02.08.2019.
 */
@Embeddable
public class IngredientQuantity {
    @Column
    private Long ingredientId;
    @Embedded
    private Weight weight;
    @Column
    private Long dishId;

    public IngredientQuantity(Long ingredientId, Long dishId, Weight weight) {
        this.ingredientId = ingredientId;
        this.dishId = dishId;
        this.weight = weight;
    }

    public Long getDishId() {
        return dishId;
    }

    public Long getIngredient() {
        return ingredientId;
    }


    public Weight getWeight() {
        return weight;
    }


    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientQuantity quantity = (IngredientQuantity) o;
        return Objects.equals(ingredientId, quantity.ingredientId) &&
                Objects.equals(weight, quantity.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, weight);
    }
}
