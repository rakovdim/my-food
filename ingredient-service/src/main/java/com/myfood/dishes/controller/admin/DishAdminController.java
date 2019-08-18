package com.myfood.dishes.controller.admin;

import com.myfood.dishes.dto.mappers.DishMapper;
import com.myfood.dishes.service.crud.DishCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.myfood.dishes.consts.DishCosnts.*;

@RestController
@RequestMapping(DISH_SERVICE_V1_URL + AND + DISHES_PATH + AND + ADMIN)
public class DishAdminController {
    private DishCrudService dishCrudService;
    private DishMapper dishMapper;

    @Autowired
    public DishAdminController(DishCrudService dishCrudService, DishMapper dishMapper) {
        this.dishCrudService = dishCrudService;
        this.dishMapper = dishMapper;
    }

    @DeleteMapping("/forceDelete/${dishId}")
    public void forceDeleteDish(UUID dishId) {
        dishCrudService.forceDeleteDish(dishId);
    }

    @DeleteMapping("/forceDeleteAll")
    public void forceDeleteAll() {
        dishCrudService.forceDeleteAllDishes();
    }

}
