package com.myfood.dishes.service;

import com.myfood.commons.utils.ids.UUIDIdGenerator;
import com.myfood.dishes.model.dish.Dish;
import com.myfood.dishes.model.dish.Status;
import com.myfood.dishes.model.dish.cooking.Receipt;
import com.myfood.dishes.model.dish.cooking.ReceiptStep;
import com.myfood.dishes.repository.CategoryRepository;
import com.myfood.dishes.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.UUID;

@Service
public class DishCrudService {
    private DishRepository dishRepository;
    private UUIDIdGenerator idGenerator;


    @Autowired
    public DishCrudService(DishRepository dishRepository, CategoryRepository categoryRepository) {
        this.dishRepository = dishRepository;
    }

    @Transactional
    public Dish createDish(Dish model) {
        Dish dishEntity = new Dish(nextId());

        dishEntity.setDescription(model.getDescription());
        dishEntity.setName(model.getName());
        dishEntity.setVisibility(model.getVisibility());
        dishEntity.setScalingStrategy(model.getScalingStrategy());
        dishEntity.setStatus(Status.NEWLY_CREATED);

        if (model.getReceipt() != null) {
            Receipt receiptEntity = new Receipt(nextId());
            Receipt receiptModel = model.getReceipt();

            receiptEntity.setComplexity(receiptModel.getComplexity());
            if (!CollectionUtils.isEmpty(receiptModel.getReceiptSteps()))
                receiptModel.getReceiptSteps().forEach(stepModel -> {
                    ReceiptStep stepEntity = new ReceiptStep(nextId());
                    stepEntity.setContent(stepModel.getContent());
                    stepEntity.setImageId(stepModel.getImageId());
                    stepEntity.setVideoId(stepModel.getVideoId());
                    receiptEntity.addReceiptStep(stepEntity);
                });
        }

        dishEntity.getSocialInfo().setAuthorId(model.getAuthorId());
        dishEntity.getSocialInfo().;
    }

    @Transactional
    public void approveDish(Long dishId) {

    }

    private UUID nextId() {
        return idGenerator.getId();
    }

}
