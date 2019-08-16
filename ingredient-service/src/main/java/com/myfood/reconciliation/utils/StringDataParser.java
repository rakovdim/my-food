package com.myfood.reconciliation.utils;

import com.myfood.reconciliation.InternalReconciliationException;
import com.myfood.reconciliation.model.dto.IngredientDTO;
import com.myfood.reconciliation.model.dto.IngredientQuantityDTO;
import com.myfood.reconciliation.model.dto.TagDTO;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class StringDataParser {

    public List<TagDTO> parseTags(String tagsString) {
        if (StringUtils.isEmpty(tagsString))
            return Collections.emptyList();

        String[] tagsArray = tagsString.split(";");

        return Arrays.stream(tagsArray).map(TagDTO::new).collect(Collectors.toList());
    }


    public List<String> parseBulletsString(String stringWithBullets) {
        return parseBulletsString(stringWithBullets, s -> s);
    }


    public <R> List<R> parseBulletsString(String stringWithBullets, Function<String, R> converter) {
        if (StringUtils.isEmpty(stringWithBullets))
            return Collections.emptyList();

        String[] arr = ("\n" + stringWithBullets).split("(\\n\\d\\d[.])|(\\n\\d\\d[)])|(\\n\\d[.])|(\\n\\d[)])");
        return Arrays.stream(arr).filter(string -> !string.isEmpty()).map(String::trim).map(converter).collect(Collectors.toList());
    }


    public IngredientQuantityDTO parseIngredientQuantity(String str) {
        if (StringUtils.isEmpty(str))
            return null;

        String[] data = str.split("-");
        if (data.length != 2)
            throw new InternalReconciliationException("Can't parse ingredient quantity: " + str + "It should be in format: $Name - $Weight$MeasureType");

        String ingredientName = data[0].trim();
        String weightAndMeasure = data[1].trim();
        String[] weightAndMeasureArray = weightAndMeasure.split("(\\d\\d\\d\\d)|(\\d\\d\\d)|(\\d\\d)|(\\d[/]\\d)|(\\d[.]\\d)|(\\d)");

        String measure = weightAndMeasureArray[1].trim();
        String weight = weightAndMeasure.split(measure)[0].trim();

        IngredientQuantityDTO ingredientQuantity = new IngredientQuantityDTO();
        ingredientQuantity.setIngredientName(ingredientName);
        ingredientQuantity.setMeasureType(measure);
        ingredientQuantity.setWeight(Double.parseDouble(weight));

        return ingredientQuantity;
    }
}
