package com.example.entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.example.utils.EntityStates;
import com.example.utils.Position;

/**
 * Tree class represents a non-animated entity that renders as a tree.
 *
 * @author Manya Sharma
 */

public class Tree extends GameEntity {
    /**
     * Constructor to create a Tree object.
     *
     * @param position  The position of the tree.
     * @param dimension The dimensions of the tree.
     * @param image     The Image the entity should use for drawing itself.
     * @param states    The states of the tree.
     */
    public Tree(Position position, Dimension dimension, GameImage image, EntityStates states) {
        super(position, dimension, image, states);
        initializeCollisionBox();
    }

    /**
     * Initializes the collision box for the tree.
     */
    @Override
    protected void initializeCollisionBox() {
        Position pos = new Position(currentPosition.getX(), currentPosition.getY());
        Dimension dim = new Dimension(size.width, size.height);
        collisionBox = new CollisionBox(pos, dim, 0, 0);
    }

    /**
     * Renders the tree on the screen with an offset.
     *
     * @param g       The graphics context.
     * @param xOffset The horizontal offset.
     * @param yOffset The vertical offset.
     */
    @Override
    public void render(Graphics g, int xOffset, int yOffset) {
        int x = (int) collisionBox.getCurrentXPos() - xOffset;
        int y = (int) collisionBox.getCurrentYPos() - yOffset;
        BufferedImage image = gameImage.getImage();
        g.drawImage(image, x, y, size.width, size.height, null);
    }
}

