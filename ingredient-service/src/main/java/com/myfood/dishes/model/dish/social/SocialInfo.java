package com.myfood.dishes.model.dish.social;

import com.myfood.dishes.model.comment.Comment;
import com.myfood.dishes.model.dish.details.Rating;

import javax.persistence.*;
import java.util.List;

@Embeddable
public class SocialInfo {
    @Embedded
    private Rating rating = new Rating();
    private String videoId;
    private String imageId;
    private Long authorId;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "dish_id")
    private List<Comment> comments;


}
