package com.example.entities;

import java.awt.*;

import com.example.utils.EntityStates;
import com.example.utils.Position;

import java.awt.image.BufferedImage;

/**
 * Class Name: House
 * Description: Represents a house entity in the game. This class extends NonAnimatedEntity to leverage
 * basic game entity functionalities such as position, dimension, and state management. The House class
 * is designed to manage and render two distinct images of houses within the game environment, showcasing
 * different styles or states of houses, such as a starting house and an ending house.
 * <p>
 * The class loads two separate images upon instantiation and draws them at specified locations on the screen.
 * This allows for visual diversity and enhances the game's graphical environment. The positions and dimensions
 * of these house images can be adjusted as needed.
 *
 * @author : Tom
 */
public class House extends GameEntity {

    public static int HOUSE_WIDTH = 128;
    public static int HOUSE_HEIGHT = 192;

    public House(Position position, Dimension dimension, GameImage image, EntityStates state) {
        super(position, dimension, image, state);
        initializeCollisionBox();
    }

    @Override
    protected void initializeCollisionBox() {
        final int IMAGE_X_OFFSET = 20;
        final int IMAGE_Y_OFFSET = 58;
        final int IMAGE_WIDTH = 86;
        final int IMAGE_HEIGHT = 50;
        Position pos = new Position(currentPosition.getX() + IMAGE_X_OFFSET, currentPosition.getY() + IMAGE_Y_OFFSET);
        Dimension dim = new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT);
        collisionBox = new CollisionBox(pos, dim, IMAGE_X_OFFSET, IMAGE_Y_OFFSET);
    }

    @Override
    public void render(Graphics g, int xOffset, int yOffset) {
        int x = (int) collisionBox.getCurrentXPos() - collisionBox.getxRenderDifference() - xOffset;
        int y = (int) collisionBox.getCurrentYPos() - collisionBox.getyRenderDifference() - yOffset;
        BufferedImage image = gameImage.getImage();
        g.drawImage(image, x, y, size.width, size.height, null);
    }
}
