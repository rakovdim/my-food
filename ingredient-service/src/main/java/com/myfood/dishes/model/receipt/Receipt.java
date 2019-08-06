package com.myfood.dishes.model.receipt;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.myfood.dishes.model.dish.details.Complexity;
import lombok.Data;

/**
 * Created by rakov on 06.08.2019.
 */
@Entity
@Data

public class Receipt
{
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Long dishId;
    @Column
    private Long authorId;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "receipt_id")
    private List<ReceiptStep> receiptSteps;
    @Column(nullable = false)
    private Complexity complexity;
}
