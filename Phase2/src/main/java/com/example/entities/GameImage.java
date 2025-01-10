package com.example.entities;

import java.awt.image.BufferedImage;

public class GameImage {

    private BufferedImage image;
    private int imageWidth;
    private int imageHeight;

    public GameImage(BufferedImage image, int imageWidth, int imageHeight) {
        this.image = image;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }
}
