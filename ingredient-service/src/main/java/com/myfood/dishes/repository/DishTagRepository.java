package com.myfood.dishes.repository;

import com.myfood.dishes.model.dish.social.DishTag;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rakov on 15.08.2019.
 */
public interface DishTagRepository extends CrudRepository<DishTag, UUID> {
    List<DishTag> findByNameIn(List<String> tagNames);

}
