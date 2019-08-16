package com.myfood.dishes.service.crud;

import com.myfood.commons.utils.ids.IdGenerator;
import com.myfood.dishes.client.IngredientsServiceClient;
import com.myfood.dishes.dto.IngredientDTO;
import com.myfood.dishes.model.DishModelException;
import com.myfood.dishes.model.dish.Category;
import com.myfood.dishes.model.dish.Dish;
import com.myfood.dishes.model.dish.cooking.Principle;
import com.myfood.dishes.model.dish.cooking.Receipt;
import com.myfood.dishes.model.dish.cooking.ReceiptStep;
import com.myfood.dishes.model.dish.social.DishTag;
import com.myfood.dishes.model.dish.system.Status;
import com.myfood.dishes.model.dish.system.Visibility;
import com.myfood.dishes.model.ingredient.IngredientQuantity;
import com.myfood.dishes.repository.CategoryRepository;
import com.myfood.dishes.repository.DishTagRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DishChangeApplier {
    private CategoryRepository categoryRepository;
    private DishTagRepository dishTagRepository;
    private IdGenerator<UUID> idGen;
    private IngredientsServiceClient ingredientsClient;


    public Dish createNewPublicDish() {
        Dish dish = new Dish(idGen.getId());

        dish.getSystemInfo().setVisibility(Visibility.PUBLIC);
        dish.getSystemInfo().setStatus(Status.NEWLY_CREATED);

        return dish;
    }

    public void applySystemData(Dish model, Dish entity) {
        entity.getSystemInfo().setScalingStrategy(model.getScalingStrategy());
    }

    public void applyPlainData(Dish model, Dish entity) {
        if (StringUtils.isEmpty(model.getName()))
            throw new DishModelException("Dish name can't be null");

        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
    }


    public void applyCategories(Dish model, Dish entity) {
        if (model.getCategories().isEmpty())
            throw new DishModelException("Can't create Dish without category");

        List<UUID> categoriesIds = model.getCategories().stream().map(Category::getId).collect(Collectors.toList());
        List<Category> foundCategories = categoryRepository.findAllByIdNotDeleted(categoriesIds);

        if (foundCategories.size() != model.getCategories().size())
            throw new DishModelException("Can't create Dish: " + model.getName() + ". Some of categories marked deleted or not found: " + categoriesIds);

        entity.getSystemInfo().addAllCategories(foundCategories);
    }


    public void applyTags(Dish model, Dish entity) {
        Set<DishTag> modelTags = model.getTags();
        if (!CollectionUtils.isEmpty(modelTags)) {
            List<DishTag> existingTags = dishTagRepository.findByNameIn(modelTags.stream().map(DishTag::getName).collect(Collectors.toList()));
            Set<String> existingTagNames = existingTags.stream().map(DishTag::getName).collect(Collectors.toSet());
            if (existingTags.size() != modelTags.size()) {
                List<DishTag> newTags = new ArrayList<>();
                modelTags.forEach(dishTag -> {
                    if (!existingTagNames.contains(dishTag.getName()))
                        newTags.add(dishTag);
                });
                entity.getSocialInfo().addAllTags(newTags);
            }
            entity.getSocialInfo().addAllTags(existingTags);
        }
    }


    public void applyReceipt(Dish model, Dish entity) {
        Receipt receiptModel = model.getReceipt();
        if (receiptModel != null) {
            Receipt receiptEntity = new Receipt(idGen.getId());
            receiptEntity.setComplexity(receiptModel.getComplexity());
            receiptModel.getReceiptSteps().forEach(stepModel -> {
                ReceiptStep stepEntity = new ReceiptStep();
                stepEntity.setImageId(stepModel.getImageId());
                stepEntity.setVideoId(stepModel.getVideoId());
                stepEntity.setContent(stepModel.getContent());
                receiptEntity.addReceiptStep(stepModel);
            });
            entity.getCookingInfo().setReceipt(receiptEntity);
        }
    }


    public void applyIngredients(Dish model, Dish entity) {
        List<IngredientQuantity> ingredients = model.getIngredients();
        if (!CollectionUtils.isEmpty(ingredients)) {
            List<UUID> ids = ingredients.stream().map(IngredientQuantity::getIngredientId).collect(Collectors.toList());
            List<IngredientDTO> ingredientDTOs = ingredientsClient.getIngredientsByIds(ids);
            model.getIngredients().forEach(ingredientModel -> {
                IngredientQuantity ingredientEntity = new IngredientQuantity(ingredientModel.getIngredientId(), ingredientModel.getVolumeMeasure(), ingredientModel.getValue());
                entity.addIngredient(ingredientEntity);
            });
        }
    }


    public void applyPrinciples(Dish model, Dish entity) {

        model.getCookingInfo().getPrinciples().forEach(principleModel -> {
            Principle principleEntity = new Principle(idGen.getId(), principleModel.getContent());
            entity.addPrinciple(principleEntity);
        });
    }


    public void applySocialData(Dish model, Dish entity) {
        entity.getSocialInfo().setImageId(model.getImageId());
        entity.getSocialInfo().setVideoId(model.getVideoId());
        entity.getSocialInfo().setAuthorId(model.getAuthorId());
    }
}
