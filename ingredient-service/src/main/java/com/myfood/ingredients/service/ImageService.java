package com.myfood.ingredients.service;

import com.myfood.ingredients.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public Image loadImage(String imageId);

    public void deleteImage(String imageId);

    public Image saveImage(MultipartFile image);

    public void updateImage(String id, MultipartFile image);
}
