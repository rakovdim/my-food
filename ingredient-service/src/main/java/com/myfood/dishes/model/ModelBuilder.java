package com.myfood.dishes.model;

import com.myfood.dishes.model.dish.Category;
import com.myfood.dishes.model.dish.Dish;
import com.myfood.dishes.model.dish.cooking.Receipt;
import org.springframework.stereotype.Component;

@Component
public class ModelBuilder {

    public static class ReceiptBuilder {
        private Receipt receipt;

        public ReceiptBuilder(Long id) {
            this.receipt = new Receipt(id);
        }
    }

    public static class CategoryBuilder {
        private Category category;

        private CategoryBuilder(Long id) {
            this.category = new Category(id);
        }

        public CategoryBuilder withName(String name) {
            category.setName(name);
            return this;
        }

        public CategoryBuilder withImage(String imageId) {
            category.setImageId(imageId);
            return this;
        }

        public Category done() {
            return category;
        }
    }

    public static class DishBuilder {
        private Dish dish;

        private DishBuilder(Long id) {
            this.dish = new Dish(id);
        }

        public DishBuilder withName(String name) {
            dish.setName(name);
            return this;
        }

        public DishBuilder withDescription(String description) {
            dish.setDescription(description);
            return this;
        }

        public DishBuilder withCategory(Category category) {
            dish.addCategory(category);
            return this;
        }
    }

    public DishBuilder newDish() {
        return null;
    }
}
