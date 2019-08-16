package com.myfood.reconciliation.service.clients.inmemory;

import com.myfood.dishes.model.dish.Category;
import com.myfood.dishes.service.crud.CategoryCrudService;
import com.myfood.reconciliation.model.dto.CategoryDTO;
import com.myfood.reconciliation.service.clients.ClientIntegration;
import com.myfood.reconciliation.service.clients.ReconcDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CategoriesInMemoryClient implements ClientIntegration<CategoryDTO> {

    private CategoryCrudService categoryCrudService;
    private ReconcDataMapper dataMapper;

    @Autowired
    public CategoriesInMemoryClient(CategoryCrudService categoryCrudService, ReconcDataMapper dataMapper) {
        this.categoryCrudService = categoryCrudService;
        this.dataMapper = dataMapper;
    }

    @Override
    public List<UUID> uploadAll(List<CategoryDTO> entities) {
        List<Category> categories = entities.stream().map(dataMapper::categoryDTOtoDO).collect(Collectors.toList());

        return categoryCrudService.createBulk(categories);
    }

    @Override
    public List<CategoryDTO> findByNames(List<String> names) {
        return null;
    }

    @Override
    public List<CategoryDTO> findByIds(List<UUID> names) {
        return null;
    }
}
