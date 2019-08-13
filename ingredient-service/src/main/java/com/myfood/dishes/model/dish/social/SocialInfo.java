package com.myfood.dishes.model.dish.social;

import com.myfood.dishes.model.comment.Comment;
import com.myfood.dishes.model.dish.details.Rating;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Embeddable
public class SocialInfo {
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

    public SocialInfo() {
        this.rating = new Rating();
        this.comments = new ArrayList<>();
    }

    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public Optional<Comment> getComment(UUID id) {
        return comments.stream().filter(comment -> comment.getId().equals(id)).findAny();
    }

    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialInfo that = (SocialInfo) o;
        return Objects.equals(rating, that.rating) &&
                Objects.equals(videoId, that.videoId) &&
                Objects.equals(imageId, that.imageId) &&
                Objects.equals(authorId, that.authorId) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rating, videoId, imageId, authorId, comments);
    }
}
