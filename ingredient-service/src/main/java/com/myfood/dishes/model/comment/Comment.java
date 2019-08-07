package com.myfood.dishes.model.comment;

import com.myfood.commons.model.entities.AuditedEntity;
import com.myfood.dishes.model.dish.details.Rating;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by rakov on 06.08.2019.
 */
@Entity
@Table(name = "dish_comments")
public class Comment extends AuditedEntity {
    @Column(name = "dish_id", insertable = false, updatable = false)
    private UUID dishId;
    private UUID authorId;
    private String text;
    @Embedded
    private Rating rating;
    private UUID parent_id;
}
