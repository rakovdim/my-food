package com.myfood.ingredients;


import com.myfood.ingredients.model.Ingredient;
import com.myfood.ingredients.model.details.Tag;
import com.myfood.ingredients.model.details.VolumeMeasure;
import com.myfood.ingredients.repository.IngredientRepository;
import com.myfood.ingredients.service.image.db.DBImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.myfood.ingredients.model.IngredientBuilder.newIngredient;


@Component
@Profile("dev")
public class ApplicationData implements CommandLineRunner {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private DBImageRepository imageRepository;

    private static final List<Tag> tags = Arrays.asList(new Tag(1L, "Завтрак"), new Tag(2L, "Ужин"));
    private Long id = 3L;

    @Override
    public void run(String... args) throws Exception {
        createTestIngredients().forEach(ingredientRepository::save);
    }

    public static void main(String[] args) throws Exception {

        byte[] bytes = imageToBytes("/Users/dima/IdeaProjects/my-food/ingredient-service/src/main/resources/pics/milk.jpg");
        for (byte b : bytes) {
            System.out.print(b);
        }
    }

    private List<Ingredient> createTestIngredients() {

        return Arrays.asList(newIngredient(generateId())
                        .withName("Молоко 3.5%")
                        .withDescription("Молоко круто!")
                        .withCalories(62)
                        .withProteins(2.9)
                        .withVolumeMeasure(VolumeMeasure.LITER)
                        .withFats(3.5)
                        .withCarbohydrates(4.7)
                        .withTag(tag("Завтрак")).build(),

                newIngredient(generateId())
                        .withName("Яйца")
                        .withDescription("Яйца круто")
                        .withCalories(155)
                        .withVolumeMeasure(VolumeMeasure.PIECE)
                        .withProteins(13)
                        .withCarbohydrates(1.1)
                        .withFats(11)
                        .withTag(tag("Завтрак"))
                        .withTag(tag("Ужин")).build());
    }

    private static byte[] imageToBytes(String ImageName) throws IOException {
        // open media
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage.getRaster();
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

        return (data.getData());
    }

    private Tag tag(String name) {
        return tags.stream().filter(tag -> tag.getName().equals(name)).findAny().get();
    }

    private Long generateId() {
        return id++;
    }
}
