package com.myfood.ingredient.repository;


import com.myfood.ingredient.model.Ingredient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, Long> {

    List<Ingredient> findByDeletedFalse(Pageable pageable);

    List<Ingredient> findByDeletedTrue(Pageable pageable);

    @EntityGraph("graph.Ingredient.eagerly")
    @Query("select ingredient from Ingredient where ingredient.id=:id AND deleted=false")
    Optional<Ingredient> findByIdEagerly(@Param("id") Long id);

    @Query("select ingredient from Ingredient where ingredient.id IN :ids AND deleted=false")
    List<Ingredient> findByIds(@Param("ids") List<Long> ids);

}
