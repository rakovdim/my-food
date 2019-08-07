package com.myfood.dishes.model.ingredient;

/**
 * Created by rakov on 02.08.2019.
 */
public class NutritionValue {
    private double calories;
    private double proteins;
    private double fats;
    private double carbohydrates;


    public NutritionValue() {
    }


    public NutritionValue(Number calories, Number proteins, Number fats, Number carbohydrates) {
        this(calories.doubleValue(), proteins.doubleValue(), fats.doubleValue(), carbohydrates.doubleValue());
    }


    public NutritionValue(double calories, double proteins, double fats, double carbohydrates) {
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }


    public double getCalories() {
        return calories;
    }


    public void setCalories(double calories) {
        this.calories = calories;
    }


    public double getProteins() {
        return proteins;
    }


    public void setProteins(double proteins) {
        this.proteins = proteins;
    }


    public double getFats() {
        return fats;
    }


    public void setFats(double fats) {
        this.fats = fats;
    }


    public double getCarbohydrates() {
        return carbohydrates;
    }


    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }


    public NutritionValue addFats(double fats) {
        setFats(this.fats + fats);
        return this;
    }


    public NutritionValue addProts(double prots) {
        setProteins(this.proteins + prots);
        return this;
    }


    public NutritionValue addCarbs(double carbs) {
        setCarbohydrates(this.carbohydrates + carbs);
        return this;
    }


    public NutritionValue addCalories(double calories) {
        setCalories(this.calories + calories);
        return this;
    }


    @Override
    public String toString() {
        return "NutritionValue{" +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }
}
