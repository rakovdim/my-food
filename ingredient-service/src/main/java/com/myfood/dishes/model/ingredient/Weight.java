package com.myfood.dishes.model.ingredient;

import javax.persistence.Embeddable;

/**
 * Created by rakov on 05.08.2019.
 */
@Embeddable
public class Weight {
    private VolumeMeasure volumeMeasure;
    private double value;


    public Weight(VolumeMeasure volumeMeasure, double weight) {
        this.volumeMeasure = volumeMeasure;
        this.value = weight;
    }


    public VolumeMeasure getVolumeMeasure() {
        return volumeMeasure;
    }


    public void setVolumeMeasure(VolumeMeasure volumeMeasure) {
        this.volumeMeasure = volumeMeasure;
    }


    public double getValue() {
        return value;
    }


    public void setValue(double weight) {
        this.value = weight;
    }


    public static Weight KILOS(double value) {
        return new Weight(VolumeMeasure.KG, value);
    }
}
