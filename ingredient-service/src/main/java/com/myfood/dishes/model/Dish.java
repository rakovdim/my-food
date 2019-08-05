package com.myfood.dishes.model;

import com.myfood.commons.model.tags.TaggedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "dishes")
public class Dish extends TaggedEntity {
    @Column(nullable = false)
    @Getter
    @Setter
    private Complexity complexity;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    private List<Comment> comments;
    @Embedded
    private Rating rating;
    @Column(nullable = false)
    @Getter
    @Setter
    private int portions;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "dish_ingredient_quantities", joinColumns = @JoinColumn(name = "dish_id"))
    private List<IngredientQuantity> ingredients = new ArrayList<>();
    @Column
    @Getter
    @Setter
    private String videoId;
    @Column
    @Getter
    @Setter
    private List<String> imageId;

    public Dish() {
    }

    public Dish(Long id) {
        super(id);
    }

    public Dish(Long id, Complexity complexity, List<Comment> comments, int portions, List<IngredientQuantity> ingredients) {
        super(id);
        this.complexity = complexity;
        this.comments = comments;
        this.rating = new Rating();
        this.portions = portions;
        this.ingredients = ingredients;
    }

    public void addIngredient(IngredientQuantity ingredient) {
        if (hasIngredient(ingredient.getIngredientId()))
            throw new ModelException("Ingredient: " + ingredient.getIngredientId() + " is already presented in this dish: " + id);

        ingredient.setDishId(id);
        ingredients.add(ingredient);
    }

    public boolean hasIngredient(Long ingredientId) {
        return getIngredient(ingredientId).isPresent();
    }

    public Optional<IngredientQuantity> getIngredient(Long ingredientId) {
        return ingredients.stream().filter(ingredient -> ingredient.getIngredientId().equals(id)).findAny();
    }

    public List<IngredientQuantity> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    public Rating getRating() {
        if (rating == null)
            rating = new Rating();

        return rating;
    }

}
