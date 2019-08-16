package com.myfood.dishes.service.crud;

import com.myfood.commons.service.DefaultCrudService;
import com.myfood.commons.service.InconsistentFlowOperationException;
import com.myfood.commons.utils.ids.IdGenerator;
import com.myfood.dishes.model.dish.Dish;
import com.myfood.dishes.model.dish.Status;
import com.myfood.dishes.model.dish.cooking.Receipt;
import com.myfood.dishes.model.dish.cooking.ReceiptStep;
import com.myfood.dishes.repository.CategoryRepository;
import com.myfood.dishes.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.UUID;

@Service
public class DishCrudService extends DefaultCrudService<Dish, DishRepository> {
    private DishRepository dishRepository;

    @Autowired
    public DishCrudService(IdGenerator<UUID> generator, DishRepository dishRepository, CategoryRepository categoryRepository) {
        super(generator);
        this.dishRepository = dishRepository;
    }

    @Override
    protected DishRepository getRepository() {
        return dishRepository;
    }

    @Override
    protected Dish createNewEntity(UUID id) {
        return new Dish(id);
    }

    @Override
    protected void fetch(Dish model, Dish destination) {

        destination.setName(model.getName());
        destination.setDescription(model.getDescription());
        destination.setVisibility(model.getVisibility());
        destination.setScalingStrategy(model.getScalingStrategy());
        destination.setStatus(Status.NEWLY_CREATED);

        if (model.getReceipt() == null)
            throw new InconsistentFlowOperationException("Can't create dish without receipt");

        Receipt receiptEntity = new Receipt();
        Receipt receiptModel = model.getReceipt();

        receiptEntity.setComplexity(receiptModel.getComplexity());
        if (CollectionUtils.isEmpty(receiptModel.getReceiptSteps()))
            throw new InconsistentFlowOperationException("Can't create receipt without steps");

        receiptModel.getReceiptSteps().forEach(stepModel -> {
            ReceiptStep stepEntity = new ReceiptStep(nextId());
            stepEntity.setContent(stepModel.getContent());
            stepEntity.setImageId(stepModel.getImageId());
            stepEntity.setVideoId(stepModel.getVideoId());
            receiptEntity.addReceiptStep(stepEntity);
        });

        destination.setAuthorId(model.getAuthorId());
    }
}
