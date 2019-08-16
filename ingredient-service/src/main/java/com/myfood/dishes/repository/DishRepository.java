package com.myfood.dishes.repository;

import com.myfood.dishes.model.dish.Dish;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DishRepository extends CrudRepository<Dish, UUID> {
    public Optional<Dish> findOptionalByIdAndDeletedFalse(UUID id);
}
