package com.myfood.dishes.model.dish.details;

import com.myfood.dishes.model.comment.Comment;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Embeddable
@Data
public class MediaInfo {
    @Embedded
    private Rating rating = new Rating();
    private String videoId;
    private String imageId;
    private Long authorId;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "dish_id")
    private List<Comment> comments;
}
