package com.myfood.dishes.model.comment;

import com.myfood.commons.model.entities.AuditedEntity;
import com.myfood.dishes.model.dish.details.Rating;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by rakov on 06.08.2019.
 */
@Entity
@Table(name = "dish_comments")
public class Comment extends AuditedEntity {
    @Column(nullable = false)
    private Long dishId;
    @Column(nullable = false)
    private Long authorId;
    @Column(nullable = false)
    private String content;
    @Embedded
    private Rating rating;
}
