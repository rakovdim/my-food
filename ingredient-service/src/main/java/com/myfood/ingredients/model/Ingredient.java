package com.myfood.ingredients.model;

import com.myfood.ingredients.model.details.FoodEnergy;
import com.myfood.ingredients.model.details.InternalDetails;
import com.myfood.ingredients.model.details.NutritionValue;
import com.myfood.ingredients.model.details.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.util.CollectionUtils.isEmpty;

@ToString
@Entity
@Table(name = "ingredients")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "ingredients-eagerly", includeAllAttributes = true)})
public class Ingredient {
    @Id
    @Getter
    private UUID id;
    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private String name;
    @Getter
    @Setter
    private String description;
    @Embedded
    private FoodEnergy foodEnergy = new FoodEnergy();
    //    @Embedded
//    private PriceDetails priceDetails;
    @Embedded
    private NutritionValue nutritionValue = new NutritionValue();
    @Embedded
    private InternalDetails internalDetails = new InternalDetails();
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "ingredient_tags",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @Getter
    @Setter
    private boolean deleted = false;

    @Getter
    @Setter
    private String imageId;


    //todo for future purpose
    @Transient
    private String createdBy;
    @Transient
    private String modifiedBy;
    @Transient
    private LocalDateTime createdWhen;
    @Transient
    private LocalDateTime modifiedWhen;

//    public Ingredient() {
//        this.foodEnergy = new FoodEnergy();
//        this.nutritionValue = new NutritionValue();
//        this.internalDetails = new InternalDetails();
//    }


    public Ingredient() {
    }

    public Ingredient(UUID id) {
        this();
        this.id = id;
    }

    public double getFats() {
        return nutritionValue.getFats();
    }

    public void setFats(double fats) {
        nutritionValue.setFats(fats);
    }

    public double getProteins() {
        return nutritionValue.getProteins();
    }

    public void setProteins(double proteins) {
        nutritionValue.setProteins(proteins);
    }

    public double getCarbohydrates() {
        return nutritionValue.getCarbohydrates();
    }

    public void setCarbohydrates(double carbohydrates) {
        nutritionValue.setCarbohydrates(carbohydrates);
    }

    public double getCalories() {
        return foodEnergy.getCalories();
    }

    public void setCalories(double calories) {
        foodEnergy.setCalories(calories);
    }

    public void addTag(Tag tag) {
        getTagsEnsure().add(tag);
    }

    public void addTags(List<Tag> newTags) {
        if (!isEmpty(newTags))
            getTagsEnsure().addAll(newTags);
    }

    public Set<Tag> getTags() {
        return getTagsEnsure();
    }

    public void update(Ingredient model) {
        setCarbohydrates(model.getCarbohydrates());
        setFats(model.getFats());
        setProteins(model.getProteins());
        setCalories(model.getCalories());
        setName(model.getName());
        setDescription(model.getDescription());
        setImageId(model.getImageId());
        if (!isEmpty(tags) && !isEmpty(model.tags)) {
            this.tags.clear();
            this.tags.addAll(model.tags);
        }
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

    private Set<Tag> getTagsEnsure() {
        if (tags == null)
            tags = new HashSet<>();
        return tags;
    }

}
