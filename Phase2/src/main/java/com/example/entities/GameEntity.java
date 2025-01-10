package com.example.entities;

import com.example.utils.EntityStates;
import com.example.utils.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Represents a general game entity within a game environment. The root class for all non grass objects drawn on the board.
 * This abstract class provides basic properties and functionalities common to all game entities, such as position, size,
 * state, and collision handling. Enforces collision box creation and rendering on its child classes.
 *
 * @author Pardeep Singh Manhas
 */
public abstract class GameEntity {
    /**
     * The current state of the entity, representing behavior or status (e.g., IDLE, MOVING).
     */
    private EntityStates state;
    /**
     * The current position of the entity on the game screen.
     */
    protected Position currentPosition;
    /**
     * The size of the entity, defining its width and height.
     */
    protected Dimension size;

    /**
     * The rectangle responsible for defining the bounds of this instance. Used for collision detection and interaction.
     */
    protected CollisionBox collisionBox;

    protected final float renderScale = 2 / 3f;

    protected GameImage gameImage;

    /**
     * Constructs a GameEntity with specified position, dimension, and state.
     * Also initializes the collision box based on the position and size of this instance.
     *
     * @param position  The initial position of the entity on the game screen. The top left origin is (0, 0).
     * @param dimension The dimensions (width and height) of the entity.
     * @param state     The initial state of the entity, defining its current behavior or status.
     * @author Pardeep Singh Manhas
     */
    public GameEntity(Position position, Dimension dimension, GameImage image, EntityStates state) {
        this.currentPosition = position;
        this.size = dimension;
        this.state = state;
        this.gameImage = image;
    }

    /**
     * Retrieves the current state of the entity.
     *
     * @return The state of the entity.
     * @author Pardeep Singh Manhas
     */
    public EntityStates getState() {
        return state;
    }

    /**
     * Sets the current state of the entity.
     *
     * @param state The new state of the entity.
     * @author Pardeep Singh Manhas
     */
    public void setState(EntityStates state) {
        this.state = state;
    }

    /**
     * Retrieves the current position of the entity (its drawn position on the screen).
     *
     * @return The current position of the entity.
     * @author Pardeep Singh Manhas
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Sets the current position of the entity (its drawn position on the screen).
     *
     * @param currentPosition The new position of the entity.
     * @author Pardeep Singh Manhas
     */
    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * Retrieves the width of the entity.
     *
     * @return The width of the entity.
     * @author Pardeep Singh Manhas
     */
    public int getWidth() {
        return size.width;
    }

    /**
     * Retrieves the height of the entity.
     *
     * @return The height of the entity.
     * @author Pardeep Singh Manhas
     */
    public int getHeight() {
        return size.height;
    }

    /**
     * Retrieves the collision box of the entity. The collision box is generally smaller than the entities size. Used for
     * collision/entity detection.
     *
     * @return The collision box of the entity.
     * @author Pardeep Singh Manhas
     */
    public Rectangle2D.Float getCollisionBox() {
        return collisionBox.getCollisionBox();
    }

    /**
     * Initializes the collision box of the entity. This abstract method must be implemented by subclasses
     * to define how the collision box is initialized based on the entity's position and size.
     *
     * @author Pardeep Singh Manhas
     */
    protected abstract void initializeCollisionBox();

    /**
     * Renders the entity with an offset. Used for Camera Movements
     *
     * @param g       The graphics object used for rendering this instance. Generally the same Object as the one used for screen drawing.
     * @param xOffset The x-offset for rendering.
     * @param yOffset The y-offset for rendering.
     * @author Pardeep Singh Manhas
     */
    public abstract void render(Graphics g, int xOffset, int yOffset);
}
