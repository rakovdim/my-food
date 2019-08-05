package com.myfood.ingredients.repository;


import com.myfood.ingredients.model.Ingredient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, Long> {

    List<Ingredient> findByDeletedFalse(Pageable pageable);

    List<Ingredient> findByDeletedTrue(Pageable pageable);

    @EntityGraph("ingredients-eagerly")
    @Query("SELECT ingredient FROM Ingredient ingredient WHERE ingredient.id=:id AND deleted=false")
    Ingredient findByIdEagerly(@Param("id") Long id);

    @Query("select ingredient from Ingredient ingredient where ingredient.id IN :ids AND deleted=false")
    List<Ingredient> findByIds(@Param("ids") List<Long> ids);

}
