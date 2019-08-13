package com.myfood.dishes.repository;

import com.myfood.dishes.model.dish.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends PagingAndSortingRepository<Category, UUID> {

    @Query("SELECT c FROM Category c WHERE c.deleted=false")
    List<Category> findAllNotDeleted(Pageable pageable);
}
