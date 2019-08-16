package com.myfood.dishes.dto;

/**
 * Created by rakov on 02.08.2019.
 */
public class NutritionInfoDTO {
    private double calories;
    private double proteins;
    private double fats;
    private double carbohydrates;


    public NutritionInfoDTO() {
    }


    public NutritionInfoDTO(Number calories, Number proteins, Number fats, Number carbohydrates) {
        this(calories.doubleValue(), proteins.doubleValue(), fats.doubleValue(), carbohydrates.doubleValue());
    }


    public NutritionInfoDTO(double calories, double proteins, double fats, double carbohydrates) {
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


    public NutritionInfoDTO addFats(double fats) {
        setFats(this.fats + fats);
        return this;
    }


    public NutritionInfoDTO addProts(double prots) {
        setProteins(this.proteins + prots);
        return this;
    }


    public NutritionInfoDTO addCarbs(double carbs) {
        setCarbohydrates(this.carbohydrates + carbs);
        return this;
    }


    public NutritionInfoDTO addCalories(double calories) {
        setCalories(this.calories + calories);
        return this;
    }


    @Override
    public String toString() {
        return "NutritionInfoDTO{" + ", calories=" + calories + ", proteins=" + proteins + ", fats=" + fats + ", carbohydrates=" + carbohydrates + '}';
    }
}
