package com.myfood.dishes.model.receipt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * Created by rakov on 06.08.2019.
 */
@Data
@Entity
@Table(name = "receipt_steps")
public class ReceiptStep
{
    @Id
    private Long id;
    @Column(nullable = false)
    private String content;
    private String imageId;
    private String videoId;
    @Column
    private Long receiptId;
}
