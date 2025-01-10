package com.example.entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.example.utils.EntityStates;
import com.example.utils.Position;

/**
 * Dynamite class represents an enemy entity that interacts and punishes the player upon interaction.
 * This class extends Enemies and implements the Interactable interface.
 * It includes methods to initialize, update, render, and interact with the knight.
 *
 * @author Manya Sharma
 */
public class Dynamite extends Enemies implements Interactable {
    // Constants for image dimensions and offsets
    public final static int DYNAMITE_SIZE = 128;
    // Punishment value for the knight upon interaction
    private final int punishValue = -25;

    /**
     * Constructor to create a Dynamite object.
     *
     * @param position  The position of the dynamite.
     * @param dimension The dimensions of the dynamite.
     * @param state     The state of the dynamite.
     */

    public Dynamite(Position position, Dimension dimension, EntityStates state, GameImage gameImage) {
        super(position, dimension, state, gameImage);
        // Initialize animations and collision box
        initializeCollisionBox();
    }

    @Override
    public void update() {
        // Update entity logic goes here
        updateState();
        updateAnimation(getState().ordinal());

    }

    @Override
    protected void updateState() {
        setState(EntityStates.MOVING);
    }

    /**
     * Initializes the collision box for the dynamite.
     */
    protected void initializeCollisionBox() {
        final int IMAGE_X_OFFSET = 47;
        final int IMAGE_Y_OFFSET = 50;
        final int IMAGE_WIDTH = 36;
        final int IMAGE_HEIGHT = 40;
        Position pos = new Position(currentPosition.getX() + IMAGE_X_OFFSET, currentPosition.getY() + IMAGE_Y_OFFSET);
        Dimension dim = new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT);
        collisionBox = new CollisionBox(pos, dim, IMAGE_X_OFFSET, IMAGE_Y_OFFSET);
    }

    @Override
    public void render(Graphics g, int xOffset, int yOffset) {
        // Render the dynamite with offset
        int x = (int) collisionBox.getCurrentXPos() - collisionBox.getxRenderDifference() - xOffset;
        int y = (int) collisionBox.getCurrentYPos() - collisionBox.getyRenderDifference() - yOffset;
        BufferedImage image = animation.getCurrentImage();
        int width = gameImage.getImageWidth();
        int height = gameImage.getImageHeight();
        g.drawImage(image, x, y, width, height, null);
    }

    /**
     * Interacts with the knight by punishing it with a negative score.
     *
     * @param knight The knight to interact with.
     */
    @Override
    public void interact(Knight knight) {
        // Punishment logic
        knight.incrementScore(this.punishValue);
    }

    /**
     * Gets the amount of punishment value for the knight upon interaction.
     *
     * @return The punishment value.
     */
    @Override
    public int getInteractableAmount() {
        // Update entity logic
        return punishValue;
    }
}