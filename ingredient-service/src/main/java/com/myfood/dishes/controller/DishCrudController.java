package com.myfood.dishes.controller;

import com.myfood.dishes.dto.DishDTO;
import com.myfood.dishes.dto.mappers.DishMapper;
import com.myfood.dishes.model.dish.Dish;
import com.myfood.dishes.service.crud.DishCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static com.myfood.dishes.consts.DishCosnts.*;

@RestController
@RequestMapping(DISH_SERVICE_V1_URL + AND + DISHES_PATH)
public class DishCrudController {

    private DishCrudService dishCrudService;
    private DishMapper dishMapper;

    @Autowired
    public DishCrudController(DishCrudService dishCrudService, DishMapper dishMapper) {
        this.dishCrudService = dishCrudService;
        this.dishMapper = dishMapper;
    }

    @PostMapping("/createPublic")
    public DishDTO createPublicDish(@Valid @RequestBody DishDTO modelDTO) {
        Dish model = dishMapper.mapDishDTOtoDOFull(modelDTO);

        Dish result = dishCrudService.createPublicDish(model);

        return dishMapper.mapDishDOtoDTOFull(result);
    }

    @GetMapping("/${dishId}")
    public DishDTO getDish(@PathVariable UUID dishId) {
        return dishMapper.mapDishDOtoDTOFull(dishCrudService.findDishById(dishId));
    }

    @DeleteMapping("/delete/${dishId}")
    public void deleteDish(UUID dishId) {
        dishCrudService.deleteDish(dishId);
    }
}
