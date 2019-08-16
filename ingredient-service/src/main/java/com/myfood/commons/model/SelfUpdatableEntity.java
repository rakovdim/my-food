package com.myfood.commons.model;

public interface SelfUpdatableEntity<ID> {
    <T> void updateFrom(T model);
}
