package com.myfood.dishes.service.crud;

import com.myfood.commons.service.EntityNotFoundException;
import com.myfood.commons.utils.ids.IdGenerator;
import com.myfood.dishes.model.dish.Category;
import com.myfood.dishes.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryCrudService {
    private IdGenerator<UUID> idGenerator;
    private CategoryRepository categoryRepository;

    public CategoryCrudService(IdGenerator<UUID> idGenerator, CategoryRepository categoryRepository) {
        this.idGenerator = idGenerator;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Category create(Category model) {
        Category entity = new Category(idGenerator.getId());

        applyChanges(model, entity);

        return categoryRepository.save(entity);
    }

    @Transactional
    public List<UUID> createBulk(List<Category> models) {
        Category entity = new Category(idGenerator.getId());
        List<UUID> ids = new ArrayList<>();
        categoryRepository.saveAll(models.stream().map(this::newCategory).collect(Collectors.toList())).forEach(category -> ids.add(category.getId()));
        return ids;
    }

    @Transactional
    public void update(UUID categoryId, Category model) {
        Category entity = findById(categoryId);

        applyChanges(model, entity);
    }

    @Transactional
    public void delete(UUID categoryId) {
        Category category = findById(categoryId);

        categoryRepository.delete(category);
    }

    @Transactional(readOnly = true)
    public Category findById(UUID id) {
        return categoryRepository.findOptionalByIdAndDeletedFalse(id).orElseThrow(() -> new EntityNotFoundException("Category: " + id + " was not found or marked for deletion"));
    }

    private Category newCategory(Category model) {
        Category entity = new Category(idGenerator.getId());

        applyChanges(model, entity);

        return entity;
    }

    private void applyChanges(Category model, Category entity) {
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setRating(model.getRating());
        entity.setImageId(model.getImageId());
    }
}
