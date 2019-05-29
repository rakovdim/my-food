package com.myfood.ingredient.repository;


import com.myfood.ingredient.model.Ingredient;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IngredientRepository extends MongoRepository<Ingredient, ObjectId> {
}
