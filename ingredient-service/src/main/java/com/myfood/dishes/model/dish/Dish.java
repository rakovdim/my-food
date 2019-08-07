package com.myfood.dishes.model.dish;

import com.myfood.commons.model.entities.AuditedEntity;
import com.myfood.dishes.model.comment.Comment;
import com.myfood.dishes.model.dish.cooking.Complexity;
import com.myfood.dishes.model.dish.cooking.Receipt;
import com.myfood.dishes.model.dish.cooking.ScalingStrategy;
import com.myfood.dishes.model.dish.details.Tag;
import com.myfood.dishes.model.dish.social.SocialInfo;
import com.myfood.dishes.model.dish.social.Visibility;
import com.myfood.dishes.model.ingredient.IngredientQuantity;
import com.myfood.dishes.model.ingredient.Weight;

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
    @Enumerated(EnumType.STRING)
    private ScalingStrategy scalingStrategy;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Embedded
    private SocialInfo socialInfo;
    @Enumerated(EnumType.STRING)
    private Visibility visibility;


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "dish_ingredients", joinColumns = @JoinColumn(name = "dish_id"))
    private List<IngredientQuantity> ingredients;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dish_id")
    private Receipt receipt;

    @ManyToMany(fetch = FetchType.LAZY)
    @MapKey
    @JoinTable(name = "dish_categories", joinColumns = @JoinColumn(name = "dish_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Map<Long, Category> categories;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "dish_tags_mapping", joinColumns = @JoinColumn(name = "dish_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dish_id")
    private List<Principle> principles;

    public Dish() {
    }

    public Dish(UUID id) {
        super(id);
        this.status = Status.NEWLY_CREATED;
        this.socialInfo = new SocialInfo();
        this.ingredients = new ArrayList<>();
        this.tags = new HashSet<>();
        this.categories = new HashMap<>();
        this.principles = new ArrayList<>();
    }

    public Dish(UUID id, String name, Receipt receipt, Visibility visibility) {
        this(id);

        this.name = name;
        setReceipt(receipt);
        setVisibility(visibility);
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

    public void addIngredient(UUID ingredientId, Weight weight) {
        IngredientQuantity ingredientQuantity = new IngredientQuantity(ingredientId, id, weight);
        ingredients.add(ingredientQuantity);
    }

    public List<Comment> getComments() {
        return socialInfo.getComments();
    }


    public void like() {
        socialInfo.getRating().like();
    }

    public void dislike() {
        socialInfo.getRating().dislike();
    }

    public Complexity getComplexity() {
        return receipt.getComplexity();
    }

    public SocialInfo getSocialInfo() {
        return socialInfo;
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

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Optional<Tag> findTag(String name) {
        return tags.stream().filter((tag -> tag.getName().equals(name))).findAny();
    }

    public void removeTag(Long id) {
        tags.removeIf(tag -> tag.getId().equals(id));
    }

    public void remoteTag(String name) {
        tags.removeIf(tag -> tag.getName().equals(name));
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Long getAuthorId() {
        return socialInfo.getAuthorId();
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
        principle.setOrdering(principles.size() + 1);
    }

    public void removePrinciple(Long principleId) {
        principles.removeIf(principle -> principle.getId().equals(principleId));
    }

    private void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}


