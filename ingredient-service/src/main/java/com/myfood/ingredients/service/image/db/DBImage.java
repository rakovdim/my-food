package com.myfood.ingredients.service.image.db;

import com.myfood.ingredients.model.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ingredient_images")
@NoArgsConstructor
@AllArgsConstructor
public class DBImage implements Image {
    @Id
    @Getter
    private String id;
    @Column(nullable = false)
    @Lob
    @Getter
    @Setter
    private byte[] rawImage;
    @Getter
    @Setter
    @Column(nullable = false)
    public long size;
    @Getter
    @Setter
    @Column(nullable = false)
    public String fileName;
    @Getter
    @Setter
    @Column(nullable = false)
    public String contentType;

    public DBImage(String id) {
        this.id = id;
    }
}
