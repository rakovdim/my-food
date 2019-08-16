package com.myfood.dishes.service.search;

import com.myfood.dishes.model.dish.Category;
import com.myfood.dishes.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategorySearchService {
    private CategoryRepository repository;

    @Autowired
    public CategorySearchService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Category> findTopCategories(int count) {
        return repository.findAllNotDeleted(getPage(count));
    }

    private Pageable getPage(int count) {
        return PageRequest.of(0, count, sortByRating());
    }

    private Sort sortByRating() {
        return Sort.by(Sort.Direction.DESC, "rating");
    }
}
