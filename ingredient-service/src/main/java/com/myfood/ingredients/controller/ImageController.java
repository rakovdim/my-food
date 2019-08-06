package com.myfood.ingredients.controller;

import com.myfood.ingredients.dto.ImageDTO;
import com.myfood.ingredients.dto.mapping.ImageMapper;
import com.myfood.ingredients.model.Image;
import com.myfood.ingredients.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.myfood.ingredients.constants.IngredientConsts.*;

@RestController
@RequestMapping(INGREDIENT_SERVICE_V1_URL + AND + IMAGES_PATH)
public class ImageController {

    private ImageService imageService;
    private ImageMapper imageMapper;

    @Autowired
    public ImageController(ImageService imageService, ImageMapper imageMapper) {
        this.imageService = imageService;
        this.imageMapper = imageMapper;
    }

    @GetMapping(value = "/{image_id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> loadImage(@PathVariable String imageId) {
        return ResponseEntity.ok().body(imageService.loadImage(imageId).getRawImage());
    }

    @PostMapping
    public ImageDTO uploadImage(@RequestParam("media") MultipartFile image) {
        Image saved = imageService.saveImage(image);

        return imageMapper.toDTO(saved);
    }
}
