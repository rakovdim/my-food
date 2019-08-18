package com.myfood.dishes.repository;

import com.myfood.dishes.model.dish.Dish;
import com.myfood.dishes.model.dish.system.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DishRepository extends CrudRepository<Dish, UUID> {
    Optional<Dish> findOptionalByIdAndDeletedFalse(UUID id);

    List<Dish> findAllByDeletedFalseAndStatusIn(Collection<Status> status, Pageable pageable);
}
