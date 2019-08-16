package com.myfood.dishes.controller;

import com.myfood.dishes.controller.dto.CategoryDTO;
import com.myfood.dishes.controller.dto.CategoryMapper;
import com.myfood.dishes.service.search.CategorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.myfood.dishes.consts.DishCosnts.*;

@RestController
@RequestMapping(DISH_SERVICE_V1_URL + AND + CATEGORIES_PATH)
public class CategoryCrudController {
    private CategorySearchService categorySearchService;
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryCrudController(CategorySearchService categorySearchService, CategoryMapper categoryMapper) {
        this.categorySearchService = categorySearchService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/top/{count}")
    public List<CategoryDTO> getTop(@PathVariable int count) {
        return categorySearchService.findTopCategories(count).stream().map(categoryMapper::categoryDTOfromDO).collect(Collectors.toList());
    }
}
