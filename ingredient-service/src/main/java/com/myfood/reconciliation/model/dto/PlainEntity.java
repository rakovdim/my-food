package com.myfood.reconciliation.model.dto;

import com.myfood.reconciliation.model.EntityType;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rakov on 13.08.2019.
 */
public class PlainEntity {
    private final Map<String, String> data = new HashMap<>();
    @Getter
    private EntityType entityType;


    public PlainEntity(EntityType entityType) {
        this.entityType = entityType;
    }


    public PlainEntity setValue(String key, String value) {
        data.put(key.toLowerCase(), value);
        return this;
    }

    public String getValue(String key) {
        return data.get(key.toLowerCase());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        PlainEntity that = (PlainEntity) o;

        return entityType != null ? entityType.equals(that.entityType) : that.entityType == null;

    }


    @Override
    public String toString() {
        return "PlainEntity{" + "data=" + data + ", entityName='" + entityType + '\'' + '}';
    }


    @Override
    public int hashCode() {
        return entityType != null ? entityType.hashCode() : 0;
    }
}

