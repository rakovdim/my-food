package com.myfood.dishes.service.crud;

import com.myfood.commons.service.EntityNotFoundException;
import com.myfood.dishes.model.dish.Dish;
import com.myfood.dishes.model.dish.cooking.Receipt;
import com.myfood.dishes.repository.DishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DishCrudService {
    private DishRepository dishRepository;
    private DishChangeApplier dishChangeApplier;


    public DishCrudService(DishRepository dishRepository, DishChangeApplier dishChangeApplier) {
        this.dishRepository = dishRepository;
        this.dishChangeApplier = dishChangeApplier;
    }

    @Transactional
    public Dish createPublicDish(Dish model) {
        Dish entity = dishChangeApplier.createNewPublicDish();

        dishChangeApplier.applyCategories(model, entity);

        dishChangeApplier.applyPlainData(model, entity);
        dishChangeApplier.applySocialData(model, entity);
        dishChangeApplier.applySystemData(model, entity);

        dishChangeApplier.applyIngredients(model, entity);
        dishChangeApplier.applyReceipt(model, entity);
        dishChangeApplier.applyTags(model, entity);
        dishChangeApplier.applyPrinciples(model, entity);

        return dishRepository.save(entity);
    }


    @Transactional
    public Dish createPrivateDish(Dish model) {
        throw new UnsupportedOperationException("Unsupported yet");
    }


    @Transactional(readOnly = true)
    public Dish findDishById(UUID id) {
        return dishRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dish: " + id + " was not found. It doesn't exist or marked for deletion"));
    }


    @Transactional
    public void deleteDish(UUID id) {
        Dish dish = findDishById(id);

        dishRepository.delete(dish);
    }


    @Transactional
    public void updateReceipt(UUID dishId, Receipt model) {
        throw new UnsupportedOperationException("Unsupported yet");
    }


    @Transactional
    public void updateDish(UUID dishId, Dish model) {
        throw new UnsupportedOperationException("Unsupported yet");
    }

    @Transactional
    public List<Dish> createPublicDishesBulk(List<Dish> models) {
        return models.stream().map(this::createPublicDish).collect(Collectors.toList());
    }


}
