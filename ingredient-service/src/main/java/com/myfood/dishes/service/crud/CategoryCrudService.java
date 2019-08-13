package com.myfood.dishes.service.crud;

import com.myfood.commons.service.DefaultCrudService;
import com.myfood.commons.utils.ids.IdGenerator;
import com.myfood.dishes.model.dish.Category;
import com.myfood.dishes.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryCrudService extends DefaultCrudService<Category, CategoryRepository> {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryCrudService(IdGenerator<UUID> idGenerator, CategoryRepository categoryRepository) {
        super(idGenerator);
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected CategoryRepository getRepository() {
        return categoryRepository;
    }

    @Override
    protected Category createNewEntity(UUID id) {
        return new Category(id);
    }

    @Override
    protected void fetch(Category model, Category destination) {
        destination.setImageId(model.getImageId());
        destination.setName(model.getName());
        destination.setDescription(model.getDescription());
        destination.setRating(model.getRating());
    }
}
