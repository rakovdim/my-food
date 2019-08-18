package com.myfood.dishes.model.dish.cooking;

import com.myfood.dishes.model.DishModelException;
import com.myfood.dishes.model.ingredient.IngredientQuantity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Embeddable
@EqualsAndHashCode
public class CookingInfo {

    @Column(updatable = false, insertable = false,name = "dish_id")
    @Getter
    private UUID dishId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    @Getter
    @Setter
    private Receipt receipt;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "dish_ingredients", joinColumns = @JoinColumn(name = "dish_id"))
    private List<IngredientQuantity> ingredients = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dish_id")
    private List<Principle> principles = new ArrayList<>();


    public CookingInfo() {
    }


    public CookingInfo(UUID dishId) {
        this.dishId = dishId;
        this.ingredients = new ArrayList<>();
        this.principles = new ArrayList<>();
    }


    public List<Principle> getPrinciples() {
        return Collections.unmodifiableList(principles);
    }


    public List<IngredientQuantity> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }


    public void addPrinciple(Principle principle) {
        principles.add(principle);
    }


    public boolean hasIngredient(UUID ingredientId) {
        return ingredients.stream().filter(ingredient -> ingredient.getIngredientId().equals(ingredientId)).findAny().isPresent();
    }


    public void addIngredient(IngredientQuantity ingredientQuantity) {
        if (!hasIngredient(ingredientQuantity.getIngredientId()))
            throw new DishModelException("Can't add ingredient: " + ingredientQuantity.getIngredientId() + " to dish: " + dishId + ". This ingredient is already present in this " + "dish");

        addIngredientInternal(ingredientQuantity);
    }


    private void addIngredientInternal(IngredientQuantity ingredientQuantity) {
        ingredients.add(ingredientQuantity);
    }
}
