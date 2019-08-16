package com.myfood.reconciliation.service.datasources;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.myfood.reconciliation.model.EntityType;
import com.myfood.reconciliation.model.dto.PlainEntity;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rakov on 13.08.2019.
 */
public class LoadedDataResponse {
    @Getter
    private DataSourceType integrationType;
    private Multimap<EntityType, PlainEntity> loadedEntities = ArrayListMultimap.create();
    private Map<String, String> errors = new HashMap<>();


    public LoadedDataResponse(DataSourceType integrationType) {
        this.integrationType = integrationType;
    }


    public void addPlainEntity(PlainEntity entity) {
        loadedEntities.put(entity.getEntityType(), entity);
    }

    public Collection<PlainEntity> getLoadedEntities(EntityType entityType) {
        return loadedEntities.get(entityType);
    }

    public void trackError(String entity, String error) {
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public boolean isEmpty(EntityType type) {
        return loadedEntities.get(type).isEmpty();
    }

    @Override
    public String toString() {
        return "LoadedDataResponse{" +
                "integrationType=" + integrationType +
                ", loadedEntities=" + loadedEntities +
                '}';
    }
}
