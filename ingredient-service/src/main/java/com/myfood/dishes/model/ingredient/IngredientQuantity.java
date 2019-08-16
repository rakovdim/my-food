package com.myfood.dishes.model.ingredient;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by rakov on 02.08.2019.
 */
@Embeddable
public class IngredientQuantity {
    @Column(nullable = false)
    @Getter
    private UUID ingredientId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter
    @Setter
    private VolumeMeasure volumeMeasure;
    @Column(nullable = false)
    @Getter
    @Setter
    private double value;


    public IngredientQuantity() {
    }


    public IngredientQuantity(UUID ingredientId, VolumeMeasure volumeMeasure, double value) {
        this.ingredientId = ingredientId;
        this.volumeMeasure = volumeMeasure;
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        IngredientQuantity that = (IngredientQuantity) o;

        if (Double.compare(that.value, value) != 0)
            return false;
        if (ingredientId != null ? !ingredientId.equals(that.ingredientId) : that.ingredientId != null)
            return false;
        return volumeMeasure == that.volumeMeasure;

    }


    @Override
    public int hashCode() {
        int result;
        long temp;
        result = ingredientId != null ? ingredientId.hashCode() : 0;
        result = 31 * result + (volumeMeasure != null ? volumeMeasure.hashCode() : 0);
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
