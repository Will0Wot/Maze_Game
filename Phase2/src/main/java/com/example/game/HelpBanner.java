package com.example.game;

import com.example.utils.ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class is a simple class that acts as a background for other instructional or informative information present
 * on the Play screen. The banner size is fixed.
 *
 * @author Pardeep Singh Manhas
 */
public class HelpBanner {
    private final Dimension size;
    private BufferedImage image;

    /**
     * Constructs a HelpBanner instance. initializing the size of the banner and loading the banner image.
     * The image source is specified by a constant identifier from ResourceLoader. If the image fails to load,
     * a runtime exception is thrown.
     *
     * @author Pardeep Singh Manhas
     */
    public HelpBanner() {
        size = new Dimension(360, 390);
        image = null;
        try {
            image = ResourceLoader.getImage(ResourceLoader.BUTTON_VERTICAL_BG);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Renders the help banner onto the game screen at the specified offset positions. Typically the top left corner
     * of the screen.
     *
     * @param g The Graphics object used for drawing the image. Typically the same one used to draw the screen.
     * @param xOffset The horizontal offset from the left where the banner should be drawn. Default offset is 0.
     * @param yOffset The vertical offset from the top where the banner should be drawn. Default offset is 0.
     * @author Pardeep Singh Manhas
     */
    public void render(Graphics g, int xOffset, int yOffset) {
        g.drawImage(image, -89, -70, size.width, size.height, null);
    }
}