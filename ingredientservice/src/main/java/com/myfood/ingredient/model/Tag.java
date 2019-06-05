package com.myfood.ingredient.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    private Long id;
    @Column(updatable = false, unique = true)
    private String name;
}
