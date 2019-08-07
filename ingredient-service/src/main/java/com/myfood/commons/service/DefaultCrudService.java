package com.myfood.commons.service;

import com.myfood.commons.model.entities.AbstractEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class DefaultCrudService<T extends AbstractEntity, ID> extends DefaultEntityService<T, ID, PagingAndSortingRepository<T, ID>> {


    @Transactional
    public T saveEntity(T model) {
        T entity = newEntity();

        updateEntityFromModel(model, entity);

        return getRepository().save(entity);
    }

    @Transactional
    public void update(ID id, T model) {
        T entity = loadEntityEnsure(id);

        updateEntityFromModel(model, entity);
    }

    @Transactional(readOnly = true)
    public T getById(ID id) {
        return loadEntityEnsure(id);
    }

    @Transactional
    public void delete(ID id) {
        T entity = loadEntityEnsure(id);

        entity.setDeleted(true);
    }

    @Transactional
    public void forceDelete(ID id) {
        T entity = loadEntityEnsure(id);

        if (!entity.isDeleted())
            throw new InconsistentFlowOperationException("Entity: " + getEntityName() + " id: " + id + " can't be deleted. It should be marked for deletion at first");

        getRepository().delete(entity);
    }

    public Iterable<T> getAll(int page, int count) {
        return getRepository().findAll(getPage(page, count));
    }

    private Pageable getPage(int page, int count) {
        return PageRequest.of(page, count, getSortCondition());
    }

    protected abstract T newEntity();

    protected abstract void updateEntityFromModel(T source, T destination);

    protected abstract Sort getSortCondition();
}
