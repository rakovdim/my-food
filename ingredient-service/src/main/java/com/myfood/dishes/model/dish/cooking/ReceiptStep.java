package com.myfood.dishes.model.dish.cooking;

import com.myfood.commons.model.entities.AuditedEntity;
import javax.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by rakov on 06.08.2019.
 */
@Embeddable
@Data
public class ReceiptStep {
    @Column(nullable = false)
    private String content;
    private String imageId;
    private String videoId;
    private int ordering;
}
