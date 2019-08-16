package com.myfood.dishes.model.dish.system;

import com.myfood.dishes.model.dish.Category;
import com.myfood.dishes.model.dish.cooking.ScalingStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by rakov on 15.08.2019.
 */
@Embeddable
@EqualsAndHashCode
public class SystemInfo {
    @Column(updatable = false, insertable = false,name = "dish_id")
    @Getter
    private UUID dishId;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Status status;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Visibility visibility;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private ScalingStrategy scalingStrategy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dish_categories", joinColumns = @JoinColumn(name = "dish_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;


    public SystemInfo() {
    }


    public SystemInfo(UUID dishId, Visibility visibility) {
        this.dishId = dishId;
        this.categories = new ArrayList<>();
        this.visibility = visibility;
        this.status = Status.NEWLY_CREATED;
        this.scalingStrategy = ScalingStrategy.DEFAULT_MULTIPLIER;
        this.categories = new ArrayList<>();
    }


    public void addCategory(Category category) {
        categories.add(category);
    }


    public void addAllCategories(Iterable<Category> categories) {
        categories.forEach(this::addCategory);
    }


    public List<Category> getCategories() {
        return Collections.unmodifiableList(categories);
    }


}
