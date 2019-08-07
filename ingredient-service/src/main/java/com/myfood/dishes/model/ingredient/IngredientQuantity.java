package com.myfood.dishes.model.ingredient;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by rakov on 02.08.2019.
 */
@Embeddable
public class IngredientQuantity {
    @Column(nullable = false)
    private UUID ingredientId;
    @Embedded
    private Weight weight;
    @Column(name = "dish_id", updatable = false, insertable = false)
    private UUID dishId;

    public IngredientQuantity(UUID ingredientId, UUID dishId, Weight weight) {
        this.ingredientId = ingredientId;
        this.dishId = dishId;
        this.weight = weight;
    }

    public UUID getDishId() {
        return dishId;
    }

    public UUID getIngredient() {
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
