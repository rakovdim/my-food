package com.myfood.commons.service;

import com.myfood.commons.model.entities.AbstractEntity;
import com.myfood.commons.utils.ids.IdGenerator;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CommonCrudOperations<T extends AbstractEntity, REPO extends CrudRepository<T, UUID>> {

    private IdGenerator<UUID> idGenerator;
    private REPO repository;


    public CommonCrudOperations(IdGenerator<UUID> idGenerator, REPO repository) {
        this.idGenerator = idGenerator;
        this.repository = repository;
    }


    public T saveEntity(T model, Function<T, T> creator) {
        T entity = creator.apply(model);

        return repository.save(entity);
    }


    public Iterable<T> saveBulk(List<T> models, Function<T, T> creator) {
        List<T> entities = models.stream().map(creator).collect(Collectors.toList());

        return repository.saveAll(entities);
    }


    public void update(UUID id, T model, BiFunction<T, T, Void> updater) {
        T entity = loadEntityCheckDeleted(id);

        updater.apply(model, entity);
    }


    public T getById(UUID id) {
        return loadEntityCheckDeleted(id);
    }


    public void delete(UUID id) {
        T entity = loadEntityCheckDeleted(id);

        entity.setDeleted(true);
    }


    public void forceDelete(UUID id) {
        T entity = loadEntityCheckDeleted(id);

        repository.delete(entity);
    }


    private T loadEntityCheckDeleted(UUID id) {
        T entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity: " + id + " was not found"));

        if (entity.isDeleted())
            throw new InconsistentFlowOperationException("Entity: " + id + " marked for deletion. No operations are allowed");

        return entity;

    }


    protected UUID nextId() {
        return idGenerator.getId();
    }
}
