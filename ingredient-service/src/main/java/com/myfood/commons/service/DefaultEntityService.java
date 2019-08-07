package com.myfood.commons.service;

import com.myfood.commons.model.entities.AbstractEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class DefaultEntityService<T extends AbstractEntity, ID, REPO extends CrudRepository<T, ID>> {

    protected T loadEntityEnsureCheckDeleted(ID id) {
        T entity = getRepository().findById(id).orElseThrow(() -> new EntityNotFoundException("Entity: " + getEntityName() + " id: " + id + " was not found"));

        if (entity.isDeleted())
            throw new InconsistentFlowOperationException(getEntityName() + " " + id + " marked for deletion. No operations are allowed");

        return entity;

    }

    protected abstract REPO getRepository();

    protected abstract String getEntityName();


}
