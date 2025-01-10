package com.example.entities;

import java.awt.*;

import com.example.utils.EntityStates;
import com.example.utils.Position;

import java.awt.image.BufferedImage;

/**
 * Represents a meat entity in the game that can be collected as a reward.
 * Extends the Reward class and implements the Interactable interface.
 *
 * @author Manya Sharma
 */
public class Meat extends Reward implements Interactable {
    // Constants for the size and offsets of the meat's image
    public static int MEAT_SIZE = 128;
    private final int rewardValue = 25;

    /**
     * Constructs a Meat object with specified parameters.
     *
     * @param position  The position of the meat.
     * @param dimension The dimension (size) of the meat.
     * @param image     The Image the entity should use for drawing itself.
     * @param state     The state of the meat.
     */
    public Meat(Position position, Dimension dimension, GameImage image, EntityStates state) {
        super(position, dimension, image, state);
        initializeCollisionBox();
    }

    /**
     * Initializes the collision box of the meat based on its position and image offsets.
     */
    @Override
    protected void initializeCollisionBox() {
        final int IMAGE_X_OFFSET = 43;
        final int IMAGE_Y_OFFSET = 60;
        final int IMAGE_WIDTH = 50;
        final int IMAGE_HEIGHT = 34;
        Position pos = new Position(currentPosition.getX() + IMAGE_X_OFFSET, currentPosition.getY() + IMAGE_Y_OFFSET);
        Dimension dim = new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT);
        collisionBox = new CollisionBox(pos, dim, IMAGE_X_OFFSET, IMAGE_Y_OFFSET);
    }

    /**
     * Renders the meat entity on the graphics context with an offset.
     *
     * @param g       The graphics context to render on.
     * @param xOffset The x-offset to apply to the rendering position.
     * @param yOffset The y-offset to apply to the rendering position.
     */
    @Override
    public void render(Graphics g, int xOffset, int yOffset) {
        int x = (int) collisionBox.getCurrentXPos() - collisionBox.getxRenderDifference() - xOffset;
        int y = (int) collisionBox.getCurrentYPos() - collisionBox.getyRenderDifference() - yOffset;
        BufferedImage image = gameImage.getImage();
        g.drawImage(image, x, y, MEAT_SIZE, MEAT_SIZE, null);
    }

    /**
     * Defines the interaction behavior between the meat entity and a Knight entity.
     *
     * @param knight The Knight entity with which interaction occurs.
     */
    @Override
    public void interact(Knight knight) {
        knight.incrementScore(this.rewardValue);
    }

    /**
     * Retrieves the amount associated with interacting with the meat entity.
     *
     * @return The amount of reward value associated with the meat.
     */
    @Override
    public int getInteractableAmount() {
        return rewardValue;
    }
}