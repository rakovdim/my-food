package com.myfood.dishes.model.comment;

import com.myfood.commons.model.entities.AuditedEntity;
import com.myfood.dishes.model.dish.details.Rating;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by rakov on 06.08.2019.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "dish_comments")
@Data
public class Comment extends AuditedEntity {
    @Column(name = "dish_id", insertable = false, updatable = false)
    private UUID authorId;
    private String text;
    @Embedded
    private Rating rating;
    private UUID parentId;

    public Comment() {
    }

    public Comment(UUID id, UUID authorId, String text) {
        super(id);
        this.authorId = authorId;
        this.text = text;
        this.rating = new Rating();
    }
}
