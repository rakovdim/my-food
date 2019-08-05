package com.myfood.dishes.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Getter
    private Long id;
    @Getter
    @Setter
    @Column(unique = true)
    private String name;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String imageId;

    public Category() {
    }

    public Category(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
