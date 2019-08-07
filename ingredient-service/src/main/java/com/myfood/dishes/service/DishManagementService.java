package com.myfood.dishes.service;

import com.myfood.commons.service.DefaultEntityService;
import com.myfood.commons.service.InconsistentFlowOperationException;
import com.myfood.dishes.model.comment.Comment;
import com.myfood.dishes.model.dish.Dish;
import com.myfood.dishes.model.dish.Status;
import com.myfood.dishes.model.dish.social.Visibility;
import com.myfood.dishes.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DishManagementService extends DefaultEntityService<Dish, UUID, DishRepository> {

    @Autowired
    private DishRepository dishRepository;

    @Transactional
    public void approveDish(UUID dishId) {
        Dish dish = loadEntityEnsureCheckDeleted(dishId);
    }

    @Transactional
    public void deprecateDish(UUID dishId) {
        loadEntityEnsureCheckDeleted(dishId).setStatus(Status.DEPRECATED);
    }

    @Transactional
    public void promoteDish(UUID dishId) {
        loadEntityEnsureCheckDeleted(dishId).setStatus(Status.PROMOTED);
    }

    @Transactional
    public void demoteDish(UUID dishId) {
        loadEntityEnsureCheckDeleted(dishId).setStatus(Status.APPROVED);
    }

    @Transactional
    public void shareDish(UUID dishId) {
        Dish dish = loadEntityEnsureCheckDeleted(dishId);
        if (!dish.getVisibility().equals(Visibility.PRIVATE))
            throw new InconsistentFlowOperationException("Dish: " + dish + " can't be shared. Only private dishes can't be share");
    }

    @Transactional
    public void unShareDish(UUID dishId) {

    }

    @Transactional
    public void commentDish(Comment model) {

    }

    @Transactional
    public void likeDish(UUID id) {

    }

    @Transactional
    public void dislikeDish(UUID id) {

    }

    private void changeStatusEnsure(Dish dish, Status newStatus) {

    }

    @Override
    protected DishRepository getRepository() {
        return dishRepository;
    }

    @Override
    protected String getEntityName() {
        return "Dish";
    }
}
