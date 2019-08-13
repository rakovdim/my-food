package com.myfood;


import com.myfood.commons.utils.ids.IdGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Component
@Profile("dev")
public class ApplicationData implements CommandLineRunner {

    private IdGenerator<UUID> idGenerator;

    private Long id = 3L;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
    }

    public static void main(String[] args) throws Exception {

        byte[] bytes = imageToBytes("/Users/dima/IdeaProjects/my-food/ingredient-service/src/main/resources/pics/milk.jpg");
        for (byte b : bytes) {
            System.out.print(b);
        }
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

    private UUID generateId() {
        return idGenerator.getId();
    }

}
