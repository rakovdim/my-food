package com.myfood.commons.model.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class NamedEntity extends AbstractEntity {

    @Column(nullable = false)
    private String name;
    @Column
    private String description;

    public NamedEntity() {
    }

    public NamedEntity(Long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
