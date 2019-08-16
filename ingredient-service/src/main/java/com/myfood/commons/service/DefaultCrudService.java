package com.myfood.commons.service;

import com.myfood.commons.model.entities.AbstractEntity;
import com.myfood.commons.utils.ids.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public abstract class DefaultCrudService<T extends AbstractEntity, REPO extends CrudRepository<T, UUID>> {

    private IdGenerator<UUID> idGenerator;

    @Autowired
    protected DefaultCrudService(IdGenerator<UUID> idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Transactional
    public T saveEntity(T model) {
        T entity = createAndFetch(model);

        return getRepository().save(entity);
    }

    @Transactional
    public List<UUID> saveBulk(List<T> models) {
        List<T> entities = models.stream().map(this::createAndFetch).collect(Collectors.toList());

        List<UUID> ids = new ArrayList<>();

        getRepository().saveAll(entities).forEach(entity -> {
            ids.add(entity.getId());
        });

        return ids;
    }


    @Transactional
    public void update(UUID id, T model) {
        T entity = loadEntityCheckDeleted(id);

        fetch(model, entity);
    }

    @Transactional(readOnly = true)
    public T getById(UUID id) {
        return loadEntityCheckDeleted(id);
    }

    @Transactional
    public void delete(UUID id) {
        T entity = loadEntityCheckDeleted(id);

        entity.setDeleted(true);
    }

    @Transactional
    public void forceDelete(UUID id) {
        T entity = loadEntityCheckDeleted(id);

        getRepository().delete(entity);
    }


    private T createAndFetch(T model) {
        T entity = createNewEntity(idGenerator.getId());

        fetch(model, entity);

        return entity;
    }


    private Pageable getPage(int page, int count) {
        return PageRequest.of(page, count, getSort());
    }

    protected T loadEntityCheckDeleted(UUID id) {
        T entity = getRepository().findById(id).orElseThrow(() -> new EntityNotFoundException("Entity: " + id + " was not found"));

        if (entity.isDeleted())
            throw new InconsistentFlowOperationException("Entity: " + id + " marked for deletion. No operations are allowed");

        return entity;

    }

    protected Sort getSort() {
        return Sort.by(Sort.Direction.ASC, "id");
    }

    protected abstract REPO getRepository();

    protected abstract T createNewEntity(UUID id);

    protected abstract void fetch(T model, T destination);

    protected UUID nextId() {
        return idGenerator.getId();
    }
}
