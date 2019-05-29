package com.myfood.ingredient.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ingredients")
public class Ingredient {
    @Id
    @Getter
    private ObjectId id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @Getter
    private FoodEnergy foodEnergy;
    @Getter
    private PriceDetails priceDetails;
    @Getter
    private NutritionValue nutritionValue;
    @Getter
    private InternalDetails internalDetails;
    private Set<Tag> tags;

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
