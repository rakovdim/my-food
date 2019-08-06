package com.myfood.dishes.model.dish;

import com.myfood.commons.model.entities.AuditedEntity;
import com.myfood.dishes.model.ModelException;
import com.myfood.dishes.model.comment.Comment;
import com.myfood.dishes.model.dish.details.*;
import com.myfood.dishes.model.ingredient.IngredientQuantity;
import com.myfood.dishes.model.ingredient.ScalingStrategy;
import com.myfood.dishes.model.ingredient.Weight;
import com.myfood.dishes.model.receipt.Receipt;

import javax.persistence.*;
import java.util.*;

/**
 * Created by rakov on 06.08.2019.
 */
@Entity
@Table(name = "dishes")
public class Dish extends AuditedEntity {

    @Column(nullable = false)
    private String name;
    private String description;
    private Visibility visibility;
    private ScalingStrategy scalingStrategy;
    private Status status;
    @Embedded
    private MediaInfo mediaInfo;


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "dish_ingredients", joinColumns = @JoinColumn(name = "dish_id"))
    private List<IngredientQuantity> ingredients;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dish_id")
    private Receipt receipt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dish_categories", joinColumns = @JoinColumn(name = "dish_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Map<Long, Category> categories;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dish_tags", joinColumns = @JoinColumn(name = "dish_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Map<Long, Tag> tags;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dish_id")
    @OrderBy("order ASC")
    private List<Principle> principles;

    public Dish() {
    }

    public Dish(Long id, Receipt receipt, Visibility visibility) {
        super(id);
        this.receipt = receipt;
        this.visibility = visibility;

        this.mediaInfo = new MediaInfo();
        this.status = Status.BASIC;
        this.tags = new HashMap<>();
        this.categories = new HashMap<>();
        this.principles = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<IngredientQuantity> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    public void addIngredient(Long ingredientId, Weight weight) {
        IngredientQuantity ingredientQuantity = new IngredientQuantity(ingredientId, id, weight);
        ingredients.add(ingredientQuantity);
    }

    public List<Comment> getComments() {
        return mediaInfo.getComments();
    }


    public void like() {
        mediaInfo.getRating().like();
    }

    public void dislike() {
        mediaInfo.getRating().dislike();
    }

    public Complexity getComplexity() {
        return receipt.getComplexity();
    }

    public MediaInfo getMediaInfo() {
        return mediaInfo;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public Map<Long, Category> getCategories() {
        return Collections.unmodifiableMap(categories);
    }

    public void addCategory(Category category) {
        categories.put(category.getId(), category);
    }

    public Map<Long, Tag> getTags() {
        return Collections.unmodifiableMap(tags);
    }

    public Optional<Tag> findTag(String name) {
        return tags.values().stream().filter((tag -> tag.getName().equals(name))).findAny();
    }

    public void removeTag(Long id) {
        tags.remove(id);
    }

    public void remoteTag(String name) {
        tags.entrySet().removeIf(tagEntry -> tagEntry.getValue().getName().equals(name));
    }

    public void addTag(Tag tag) {
        tags.put(tag.getId(), tag);
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Long getAuthorId() {
        return mediaInfo.getAuthorId();
    }

    public ScalingStrategy getScalingStrategy() {
        return scalingStrategy;
    }

    public void setScalingStrategy(ScalingStrategy scalingStrategy) {
        this.scalingStrategy = scalingStrategy;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Principle> getPrinciples() {
        return Collections.unmodifiableList(principles);
    }

    public void addPrinciple(Principle principle) {
        principles.add(principle);
        principle.setOrder(principles.size() + 1);
    }

    public void reorderPrinciple(Long principleId, int newPosition) {
        if (newPosition >= principles.size())
            throw new ModelException("Principle: " + principleId + ", cant be moved to: " + newPosition + ". Current principles count: " + principles.size());


    }
}


