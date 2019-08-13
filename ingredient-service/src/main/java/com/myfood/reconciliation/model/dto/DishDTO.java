package com.myfood.reconciliation.model.dto;

import java.util.UUID;
import lombok.Getter;

/**
 * Created by rakov on 09.08.2019.
 */
public class DishDTO {
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private String complexity;
    @Getter
    private String visibility;
    @Getter
    private String imageId;
    @Getter
    private String videoId;
    @Getter
    private UUID authorId;
    @Getter
    private int likes;


}
