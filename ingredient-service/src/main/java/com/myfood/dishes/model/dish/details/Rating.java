package com.myfood.dishes.model.dish.details;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * Created by rakov on 06.08.2019.
 */
@Embeddable
public class Rating {
    @Column
    @Getter
    private int likes;

    public void like() {
        likes++;
    }


    public void unlike() {
        likes--;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return likes == rating.likes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(likes);
    }
}
