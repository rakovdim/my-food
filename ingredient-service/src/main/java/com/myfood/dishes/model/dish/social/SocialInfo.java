package com.myfood.dishes.model.dish.social;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Embeddable
@EqualsAndHashCode
public class SocialInfo {
    @Column(updatable = false, insertable = false,name = "dish_id")
    @Getter
    private UUID dishId;
    @Embedded
    @Getter
    private Rating rating;
    @Getter
    @Setter
    private String videoId;
    @Getter
    @Setter
    private String imageId;
    @Getter
    @Setter
    private UUID authorId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "dish_id")
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "dish_tags_mapping", joinColumns = @JoinColumn(name = "dish_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<DishTag> dishTags;


    public SocialInfo() {
    }


    public SocialInfo(UUID dishId) {
        this.dishId = dishId;
        this.rating = new Rating();
        this.comments = new ArrayList<>();
        this.dishTags = new HashSet<>();
    }


    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }


    public void addComment(Comment comment) {
        comments.add(comment);
    }


    public Set<DishTag> getDishTags() {
        return Collections.unmodifiableSet(dishTags);
    }


    public void addTag(DishTag dishTag) {
        dishTags.add(dishTag);
    }


    public void addAllTags(Iterable<DishTag> tags) {
        tags.forEach(this::addTag);
    }


}
