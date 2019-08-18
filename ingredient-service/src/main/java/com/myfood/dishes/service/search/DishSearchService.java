package com.myfood.dishes.service.search;

import com.myfood.dishes.model.dish.Dish;
import com.myfood.dishes.model.dish.system.Status;
import com.myfood.dishes.repository.DishRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class DishSearchService {
    private DishRepository dishRepository;

    public DishSearchService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Transactional(readOnly = true)
    public List<Dish> findTopRatedDishes(int page, int count) {
        return dishRepository.findAllByDeletedFalseAndStatusIn(Arrays.asList(Status.APPROVED, Status.PROMOTED), ratingPage(page, count));
    }

    private PageRequest ratingPage(int page, int count) {
        return PageRequest.of(page, count, sortByRating());
    }

    private Sort sortByRating() {
        return Sort.by(Sort.Direction.DESC, "rating");
    }
}
