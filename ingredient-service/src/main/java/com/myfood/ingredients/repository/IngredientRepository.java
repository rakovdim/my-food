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
    @Query("SELECT i FROM Ingredient i WHERE i.id=:id AND i.deleted=false")
    Ingredient findByIdEagerly(@Param("id") Long id);

    @Query("select i from Ingredient i where i.id IN :ids AND i.deleted=false")
    List<Ingredient> findByIds(@Param("ids") List<Long> ids);

    @Query("select i from Ingredient i where i.name IN :names AND i.deleted=false")
    List<Ingredient> findByNames(@Param("names") List<String> names, Pageable pageable);

}
