package com.myfood;


import com.myfood.ingredients.service.image.db.DBImage;
import com.myfood.ingredients.service.image.db.DBImageRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class ApplicationData {
    @Value("classpath:pics/breakfast.jpg")
    private Resource breakfast;

    @Value("classpath:pics/diner.jpg")
    private Resource diner;

    @Value("classpath:pics/supper.jpg")
    private Resource supper;


    @Value("classpath:pics/herznaet.png")
    private Resource herznaet;

    @Value("classpath:pics/drinks.jpg")
    private Resource drinks;

    @Autowired
    private DBImageRepository imageRepository;


    public Map<String, Resource> getImages() {
        return new HashMap<String, Resource>() {
            {
                put(breakfast.getFilename(), breakfast);
                put(diner.getFilename(), diner);
                put(supper.getFilename(), supper);
                put(herznaet.getFilename(), herznaet);
                put(drinks.getFilename(), drinks);

            }

        };
    }

    public void uploadImages() throws Exception {
        storeImages(getImages());
    }


    private void storeImages(Map<String, Resource> images) throws Exception {
        for (Map.Entry<String, Resource> imageEntry : images.entrySet()) {
            byte[] imgBytes = IOUtils.toByteArray(imageEntry.getValue().getInputStream());

            DBImage image = new DBImage(imageEntry.getKey());
            image.setContentType(MediaType.IMAGE_JPEG_VALUE);
            image.setFileName(imageEntry.getKey());
            image.setRawImage(imgBytes);
            image.setSize(100);

            imageRepository.save(image);
        }
    }
}
