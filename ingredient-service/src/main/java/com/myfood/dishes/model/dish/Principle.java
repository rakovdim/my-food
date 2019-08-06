package com.myfood.dishes.model.dish;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dish_principles")
public class Principle {
    @Id
    private Long id;
    @Column(nullable = false)
    private String text;
    private int order;

    public Principle(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getOrder() {
        return order;
    }

    void setOrder(int order) {
        this.order = order;
    }


}
