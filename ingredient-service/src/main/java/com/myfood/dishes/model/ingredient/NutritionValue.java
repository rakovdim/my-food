package com.myfood.dishes.model.ingredient;

/**
 * Created by rakov on 02.08.2019.
 */
public class NutritionValue
{
    private double calories;
    private double prots;
    private double fats;
    private double carbs;


    public NutritionValue()
    {
    }


    public NutritionValue(Number calories, Number prots, Number fats, Number carbs)
    {
        this(calories.doubleValue(), prots.doubleValue(), fats.doubleValue(), carbs.doubleValue());
    }


    public NutritionValue(double calories, double prots, double fats, double carbs)
    {
        this.calories = calories;
        this.prots = prots;
        this.fats = fats;
        this.carbs = carbs;
    }


    public double getCalories()
    {
        return calories;
    }


    public void setCalories(double calories)
    {
        this.calories = calories;
    }


    public double getProts()
    {
        return prots;
    }


    public void setProts(double prots)
    {
        this.prots = prots;
    }


    public double getFats()
    {
        return fats;
    }


    public void setFats(double fats)
    {
        this.fats = fats;
    }


    public double getCarbs()
    {
        return carbs;
    }


    public void setCarbs(double carbs)
    {
        this.carbs = carbs;
    }


    public NutritionValue addFats(double fats)
    {
        setFats(this.fats + fats);
        return this;
    }


    public NutritionValue addProts(double prots)
    {
        setProts(this.prots + prots);
        return this;
    }


    public NutritionValue addCarbs(double carbs)
    {
        setCarbs(this.carbs + carbs);
        return this;
    }


    public NutritionValue addCalories(double calories)
    {
        setCalories(this.calories + calories);
        return this;
    }


    @Override
    public String toString()
    {
        return "NutritionValue{" +
            ", calories=" + calories +
            ", prots=" + prots +
            ", fats=" + fats +
            ", carbs=" + carbs +
            '}';
    }
}
