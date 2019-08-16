package com.myfood.ingredients.model;

public interface Image {
    public String getId();

    public long getSize();

    public String getFileName();

    public String getContentType();

    public byte[] getRawImage();
}
