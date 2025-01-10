package com.example.entities;

import java.awt.*;

import com.example.utils.EntityStates;
import com.example.utils.Position;

import java.awt.image.BufferedImage;

/**
 * Coin class represents a reward entity in the game that can be collected by the player.
 * This class extends Reward and implements the Interactable interface.
 *
 * @author Manya Sharma
 */


public class Coin extends Reward implements Interactable {
    // Constants for image dimensions and offsets
    public static int COIN_SIZE = 128;
    // Reward value for collecting the coin
    private final int rewardValue = 50;

    /**
     * Constructor to create a Coin object.
     *
     * @param position  The position of the coin.
     * @param dimension The dimensions of the coin.
     * @param state     The state of the coin.
     */

    public Coin(Position position, Dimension dimension, GameImage image, EntityStates state) {
        super(position, dimension, image, state);
        initializeCollisionBox();
    }

    /**
     * Initializes the collision box for the coin.
     * Adjusts the collision box based on image offsets.
     */
    @Override
    protected void initializeCollisionBox() {
        final int IMAGE_X_OFFSET = 50;
        final int IMAGE_Y_OFFSET = 60;
        final int IMAGE_WIDTH = 38;
        final int IMAGE_HEIGHT = 34;
        Position pos = new Position(currentPosition.getX() + IMAGE_X_OFFSET, currentPosition.getY() + IMAGE_Y_OFFSET);
        Dimension dim = new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT);
        collisionBox = new CollisionBox(pos, dim, IMAGE_X_OFFSET, IMAGE_Y_OFFSET);
    }

    /**
     * Renders the coin on the screen with an offset.
     *
     * @param g       The graphics context.
     * @param xOffset The horizontal offset.
     * @param yOffset The vertical offset.
     */
    @Override
    public void render(Graphics g, int xOffset, int yOffset) {
        int x = (int) collisionBox.getCurrentXPos() - collisionBox.getxRenderDifference() - xOffset;
        int y = (int) collisionBox.getCurrentYPos() - collisionBox.getyRenderDifference() - yOffset;
        BufferedImage image = gameImage.getImage();
        g.drawImage(image, x, y, size.width, size.height, null);
    }

    /**
     * Interacts with the knight by increasing its score.
     *
     * @param knight The knight to interact with.
     */
    @Override
    public void interact(Knight knight) {
        knight.incrementScore(this.rewardValue);
    }

    /**
     * Gets the amount of reward provided by this interactable.
     *
     * @return The amount of reward provided.
     */
    @Override
    public int getInteractableAmount() {
        return rewardValue;
    }
}