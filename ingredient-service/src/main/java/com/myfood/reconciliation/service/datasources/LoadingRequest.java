package com.myfood.reconciliation.service.datasources;

import com.myfood.reconciliation.model.EntityType;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

/**
 * Created by rakov on 09.08.2019.
 */
public class LoadingRequest {
    @Getter
    private List<EntityType> entityTypes;

    public LoadingRequest(List<EntityType> entityTypes) {
        this.entityTypes = entityTypes;
    }

    public LoadingRequest(EntityType entityTypes) {
        this(Collections.singletonList(entityTypes));

    }
}
