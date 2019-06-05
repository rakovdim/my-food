package com.myfood.ingredient.service.impl;

import com.myfood.ingredient.events.Events;
import com.myfood.ingredient.model.Ingredient;
import com.myfood.ingredient.repository.IngredientRepository;
import com.myfood.ingredient.service.EntityNotFoundException;
import com.myfood.ingredient.service.IngredientCrudService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@Log4j2
public class IngredientCrudServiceImpl implements IngredientCrudService {
    private IngredientRepository repository;
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public IngredientCrudServiceImpl(IngredientRepository repository, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public Ingredient createNewIngredient(Ingredient ingredient) {
        log.trace("Creating new ingredient: " + ingredient);

        return repository.save(ingredient);
    }

    @Transactional(readOnly = true)
    public Ingredient getIngredient(Long id) {
        log.trace("Looking for ingredient: " + id);

        return findIngredientEnsure(id);
    }

    @Transactional(readOnly = true)
    public Ingredient getIngredientWithTags(Long id) {
        log.trace("Looking for ingredient: " + id);

        return findIngredientEagerly(id);
    }

    @Transactional(readOnly = true)
    public List<Ingredient> findByIds(List<Long> ids) {
        return repository.findByIds(ids);
    }

    @Transactional(readOnly = true)
    public Iterable<Ingredient> findAllIngredients(int page, int count) {
        return repository.findByDeletedFalse(getPage(page, count));
    }

    @Transactional
    public void updateIngredient(Long id, Ingredient model) {
        Ingredient ingredient = findIngredientEnsure(id);

        ingredient.updateFromModel(model);
    }

    @Transactional
    public void deleteIngredient(Long id) {
        Ingredient ingredient = findIngredientEnsure(id);

        ingredient.setDeleted(true);

        eventPublisher.publishEvent(Events.deleted(id));
    }


    private Ingredient findIngredientEnsure(Long id) {
        return findIngredientEnsure(id, () -> repository.findById(id));
    }

    private Ingredient findIngredientEagerly(Long id) {
        return findIngredientEnsure(id, () -> repository.findByIdEagerly(id));
    }

    private Ingredient findIngredientEnsure(Long id, Supplier<Optional<Ingredient>> supplier) {
        return supplier.get().orElseThrow(() -> new EntityNotFoundException("Ingredient: " + id + " not found"));
    }

    private Pageable getPage(int page, int count) {
        return PageRequest.of(page, count, Sort.by(Sort.Direction.ASC, "id"));
    }
}
