package com.myfood.dishes.model.ingredient;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by rakov on 05.08.2019.
 */
@Embeddable
public class Weight {
    @Enumerated(EnumType.STRING)
    private VolumeMeasure volumeMeasure;
    @Column(nullable = false)
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
