package com.myfood.ingredients.service.image.db;

import com.myfood.ingredients.model.Image;
import com.myfood.ingredients.service.EntityNotFoundException;
import com.myfood.ingredients.service.ImageService;
import com.myfood.ingredients.service.InternalIngredientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Service
public class DBBasedImageService implements ImageService {

    private DBImageRepository repository;

    @Autowired
    public DBBasedImageService(DBImageRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional(readOnly = true)
    public DBImage loadImage(String imageId) {
        return repository.findById(imageId).orElseThrow(() -> new EntityNotFoundException("Image: " + imageId + " not found"));
    }

    @Override
    @Transactional
    public void deleteImage(String imageId) {
        repository.deleteById(imageId);
    }

    @Override
    @Transactional
    public Image saveImage(MultipartFile source) {
        DBImage image = createImage();

        setData(image, source);

        return repository.save(image);
    }

    @Override
    @Transactional
    public void updateImage(String id, MultipartFile source) {
        DBImage image = loadImage(id);

        setData(image, source);
    }

    private DBImage createImage() {
        return new DBImage(UUID.randomUUID().toString());
    }

    private static void setData(DBImage target, MultipartFile source) {
        try {
            target.setContentType(source.getContentType());
            target.setSize(source.getSize());
            target.setFileName(source.getOriginalFilename());
            target.setRawImage(source.getBytes());
        } catch (IOException e) {
            throw new InternalIngredientException(e);
        }
    }
}
