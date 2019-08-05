package com.myfood.dishes.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Rating {
    @Column
    private int likes;
    @Column
    private int dislikes;

    public void like() {
        likes++;
    }

    public void dislike() {
        dislikes++;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

}
