package com.myfood.ingredient.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@ToString
@Entity
@Table(name = "ingredients")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.Ingredient.eagerly", includeAllAttributes = true)})
public class Ingredient {
    @Id
    @Getter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @Getter
    @Embedded
    private FoodEnergy foodEnergy;
    @Getter
    @Embedded
    private PriceDetails priceDetails;
    @Getter
    @Embedded
    private NutritionValue nutritionValue;
    @Getter
    @Embedded
    private InternalDetails internalDetails;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ingredient_tags",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @Cascade({CascadeType.PERSIST})
    private Set<Tag> tags;
    @Getter
    @Setter
    private boolean deleted;


    //todo for future purpose
    @Transient
    private String createdBy;
    @Transient
    private String modifiedBy;
    @Transient
    private LocalDateTime createdWhen;
    @Transient
    private LocalDateTime modifiedWhen;

    public Ingredient(String name, String description, FoodEnergy foodEnergy, PriceDetails priceDetails, NutritionValue nutritionValue, InternalDetails internalDetails) {
        this.name = name;
        this.description = description;
        this.foodEnergy = foodEnergy;
        this.priceDetails = priceDetails;
        this.nutritionValue = nutritionValue;
        this.internalDetails = internalDetails;
        this.deleted = false;
    }

    public void addTag(Tag tag) {
        getTagsEnsure().add(tag);
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(getTagsEnsure());
    }

    public void updateFromModel(Ingredient model) {

    }

    private Set<Tag> getTagsEnsure() {
        if (tags == null)
            tags = new HashSet<>();
        return tags;
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
