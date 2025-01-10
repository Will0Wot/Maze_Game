package com.example.entities;

import com.example.utils.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Represents a collision detection box associated with an entity.
 * This class is used to manage the bounding rectangle for an {@link GameEntity}. Utilized to enable collision detection and
 * rendering adjustments relative to the {@link GameEntity}'s position and the {@link com.example.game.GameCamera}.
 * The collision box is solely defined by a position and dimension, and it can be mutated or queried to adjust these
 * features.
 * In practice, a Collision Box should be smaller than the entity it is associated with, and should be offset to be
 * more central to the {@link GameEntity}.
 *
 * @author Pardeep Singh Manhas
 */
public class CollisionBox {
    /**
     * The rectangle defining the collision area.
     */
    private final Rectangle2D.Float collisionBox;
    /**
     * Horizontal offset for rendering the collision box relative to the {@link GameEntity}'s actual position.
     */
    private final int xRenderDifference;
    /**
     * Vertical offset for rendering the collision box relative to the {@link GameEntity}'s actual position.
     */
    private final int yRenderDifference;

    /**
     * Constructs a CollisionBox with specified position, dimensions, and rendering offsets.
     *
     * @param pos               The position of the {@link GameEntity} (top-left corner).
     * @param dim               The dimensions of the Collision Box. Should be smaller than the {@link GameEntity}'s dimensions.
     * @param xRenderDifference The horizontal rendering offset. Used to center the box relative to the {@link GameEntity}.
     * @param yRenderDifference The vertical rendering offset. Used to center the box relative to the {@link GameEntity}.
     * @author Pardeep Singh Manhas
     */
    public CollisionBox(Position pos, Dimension dim, int xRenderDifference, int yRenderDifference) {
        this.xRenderDifference = xRenderDifference;
        this.yRenderDifference = yRenderDifference;
        this.collisionBox = new Rectangle2D.Float(pos.getX(), pos.getY(), dim.width, dim.height);
    }

    /**
     * /**
     * Draws the collision box of the entity for debugging purposes.
     *
     * @param g The graphics object used for drawing the collision box.
     * @author Pardeep Singh Manhas
     */
    public void drawCollisionBox(Graphics g) {
        g.drawRect((int) collisionBox.x, (int) collisionBox.y, (int) collisionBox.width, (int) collisionBox.height);
    }

    /**
     * Retrieves the collision box of the entity. The collision box is generally smaller than the entities size. Used for
     * collision/entity detection.
     *
     * @return The collision box of the entity.
     * @author Pardeep Singh Manhas
     */
    public Rectangle2D.Float getCollisionBox() {
        return collisionBox;
    }

    /**
     * Returns the horizontal rendering offset.
     *
     * @return The horizontal offset for rendering the collision box.
        * @author Pardeep Singh Manhas
     */
    public int getxRenderDifference() {
        return xRenderDifference;
    }

    /**
     * Returns the vertical rendering offset.
     *
     * @return The vertical offset for rendering the collision box.
     * @author Pardeep Singh Manhas
     */
    public int getyRenderDifference() {
        return yRenderDifference;
    }

    /**
     * Retrieves the current horizontal position of the collision box.
     *
     * @return The x-coordinate of the collision box.
     * @author Pardeep Singh Manhas
     */
    public float getCurrentXPos() {
        return collisionBox.x;
    }

    /**
     * Retrieves the current vertical position of the collision box.
     *
     * @return The y-coordinate of the collision box.
     * @author Pardeep Singh Manhas
     */
    public float getCurrentYPos() {
        return collisionBox.y;
    }

    /**
     * Sets the horizontal position of the collision box.
     *
     * @param newX The new x-coordinate for the collision box.
     * @author Pardeep Singh Manhas
     */
    public void setCurrentXPos(float newX) {
        collisionBox.x = newX;
    }

    /**
     * Sets the vertical position of the collision box.
     *
     * @param newY The new y-coordinate for the collision box.
     * @author Pardeep Singh Manhas
     */
    public void setCurrentYPos(float newY) {
        collisionBox.y = newY;
    }

    /**
     * Returns the width of the collision box.
     *
     * @return The width of the collision box.
     * @author Pardeep Singh Manhas
     */
    public float getWidth() {
        return collisionBox.width;
    }

    /**
     * Returns the height of the collision box.
     *
     * @return The height of the collision box.
     * @author Pardeep Singh Manhas
     */
    public float getHeight() {
        return collisionBox.height;
    }
}
