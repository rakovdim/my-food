package com.myfood.ingredient.model;

import lombok.Data;

import java.util.Currency;

@Data
public class PriceDetails {
    private double amount;
    private Currency currency;
}
