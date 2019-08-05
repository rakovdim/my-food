package com.myfood.ingredients.model.details;

import lombok.Data;

import javax.persistence.Embeddable;
import java.util.Currency;

@Data
@Embeddable
public class PriceDetails {
    private float amount;
    private Currency currency;
}
