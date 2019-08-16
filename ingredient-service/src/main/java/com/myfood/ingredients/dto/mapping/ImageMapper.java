package com.myfood.ingredients.dto.mapping;

import com.myfood.ingredients.dto.ImageDTO;
import com.myfood.ingredients.model.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    public ImageDTO toDTO(Image image) {
        return new ImageDTO(image.getId(), image.getContentType());
    }

}
