package com.myfood.commons.model.tags;

import com.myfood.commons.model.BaseEntity;

import javax.persistence.*;
import java.util.*;

@MappedSuperclass
public abstract class TaggedEntity extends BaseEntity {

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "entity_tags",
            joinColumns = @JoinColumn(name = "entity_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    public TaggedEntity() {
    }

    public TaggedEntity(Long id) {
        super(id);
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(getTagsEnsure());
    }

    public void addTag(Tag tag) {
        getTagsEnsure().add(tag);
    }

    public void removeTag(Long tagId) {
        getTagsEnsure().removeIf(tag -> tagId.equals(tag.getId()));
    }

    public Optional<Tag> findTag(String name) {
        return getTagsEnsure().stream().filter((tag -> tag.getName().equals(name))).findAny();
    }

    public boolean hasTag(String name) {
        return findTag(name).isPresent();
    }

    private Set<Tag> getTagsEnsure() {
        return tags;
    }
}
