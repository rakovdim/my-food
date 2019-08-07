package com.myfood.dishes.repository;

import com.myfood.dishes.model.dish.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
}
