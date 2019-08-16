package com.myfood.dishes.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.myfood.commons.service.EntityNotFoundException;
import com.myfood.commons.service.InconsistentFlowOperationException;
import com.myfood.commons.utils.ids.IdGenerator;
import com.myfood.dishes.model.comment.Comment;
import com.myfood.dishes.model.dish.Dish;
import com.myfood.dishes.model.dish.Status;
import com.myfood.dishes.model.dish.social.Visibility;
import com.myfood.dishes.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.UUID;

import static com.myfood.dishes.model.dish.Status.*;

@Service
public class DishManagementService {

    private static final Multimap<Status, Status> availableTransitions = ArrayListMultimap.create();

    static {
        availableTransitions.putAll(NEWLY_CREATED, Arrays.asList(DEPRECATED, APPROVED, PROMOTED));
        availableTransitions.putAll(APPROVED, Arrays.asList(PROMOTED, DEPRECATED));
        availableTransitions.put(DEPRECATED, APPROVED);
        availableTransitions.putAll(PROMOTED, Arrays.asList(APPROVED, DEPRECATED));
    }

    private IdGenerator<UUID> idGenerator;
    private DishRepository dishRepository;

    @Autowired
    public DishManagementService(IdGenerator<UUID> idGenerator, DishRepository dishRepository) {
        this.idGenerator = idGenerator;
        this.dishRepository = dishRepository;
    }

    @Transactional
    public void approveDish(UUID dishId) {
        changeStatusEnsure(dishId, APPROVED);
    }

    @Transactional
    public void deprecateDish(UUID dishId) {
        changeStatusEnsure(dishId, DEPRECATED);
    }

    @Transactional
    public void promoteDish(UUID dishId) {
        changeStatusEnsure(dishId, PROMOTED);
    }

    @Transactional
    public void shareDish(UUID dishId) {
        Dish dish = loadEntityCheckDeleted(dishId);

        if (!dish.getVisibility().equals(Visibility.PRIVATE))
            throw new InconsistentFlowOperationException("Dish: " + dish + " can't be shared. Only private dishes can be share. This dish visibility: " + dish.getVisibility());

        dish.setVisibility(Visibility.SHARED);
    }

    @Transactional
    public void unShareDish(UUID dishId) {
        Dish dish = loadEntityCheckDeleted(dishId);

        if (!dish.getVisibility().equals(Visibility.SHARED))
            throw new InconsistentFlowOperationException("Dish: " + dish + " can't be unshared. Only shared dishes c be unshared. This dish visibility: " + dish.getVisibility());

        dish.setVisibility(Visibility.PRIVATE);
    }

    @Transactional
    public void commentDish(UUID dishId, Comment model) {
        Dish dish = loadEntityCheckDeleted(dishId);

        Comment comment = new Comment(idGenerator.getId(), model.getAuthorId(), model.getText());

        dish.getSocialInfo().addComment(comment);
    }

    @Transactional
    public void likeDish(UUID id) {

    }

    @Transactional
    public void unlikeDish(UUID id) {
    }

    @Transactional
    public void likeComment(UUID dishId, UUID commentId) {
        Dish dish = loadEntityCheckDeleted(dishId);

//        Comment comment = dish.getSocialInfo().getComment(commentId).orElseThrow(() -> new EntityNotFoundException("Comment: " + commentId + " for dish: " + dishId + " was not found"));
//
//        comment.getRating().like();
    }


    @Transactional
    public void answerOnComment(UUID dishId, UUID comment, Comment model) {

    }

    private void changeStatusEnsure(UUID dishId, Status newStatus) {
        Dish dish = loadEntityCheckDeleted(dishId);

        Status oldStatus = dish.getStatus();

        if (!availableTransitions.containsEntry(oldStatus, newStatus))
            throw new InconsistentFlowOperationException("Dish: " + dish.getId() + " cant be moved from status: " + oldStatus + " to new status: " + newStatus + ". Transition is not possible");

        dish.setStatus(newStatus);
    }

    protected Dish loadEntityCheckDeleted(UUID id) {
        Dish entity = dishRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity: " + id + " was not found"));

        if (entity.isDeleted())
            throw new InconsistentFlowOperationException("Entity: " + id + " marked for deletion. No operations are allowed");

        return entity;

    }
}
