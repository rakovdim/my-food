package com.myfood.dishes.model.dish.cooking;

import com.myfood.commons.model.entities.AuditedEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by rakov on 06.08.2019.
 */
@Entity
public class Receipt extends AuditedEntity {
    @CollectionTable(name = "receipt_steps", foreignKey = @ForeignKey(name = "receipt_id"))
    @ElementCollection(fetch = FetchType.LAZY)
    private List<ReceiptStep> steps = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Complexity complexity;


    public Receipt() {
    }


    public Receipt(UUID id) {
        super(id);
        this.steps = new ArrayList<>();
    }


    public Receipt(UUID id, List<ReceiptStep> steps, Complexity complexity) {
        this(id);

        this.complexity = complexity;
        addReceiptSteps(steps);
    }


    public void addReceiptSteps(List<ReceiptStep> steps) {
        steps.forEach(this::addReceiptStep);
    }


    public void addReceiptStep(ReceiptStep step) {
        step.setOrdering(steps.size() + 1);
        steps.add(step);
    }


    public List<ReceiptStep> getReceiptSteps() {
        return Collections.unmodifiableList(steps);
    }


    public Complexity getComplexity() {
        return complexity;
    }


    public void setComplexity(Complexity complexity) {
        this.complexity = complexity;
    }
}
