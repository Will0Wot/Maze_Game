package com.example.entities;

import com.example.utils.EntityStates;
import com.example.utils.Position;

import java.awt.*;

/**
 * Represents an abstract animated game entity within a game environment. Extends GameEntity class to add animation capabilities.
 * An animated game entity is characterized by a series of images (frames) that are displayed in sequence
 * to create the illusion of motion. Animations are stored in a 2Dimensional array where each row represents a different animation
 * (e.g., walking) and each column within a row represents a frame in that animation.
 * The animation speed is  managed internally and is constant
 *
 * @author Pardeep Singh Manhas
 */
public abstract class AnimatedGameEntity extends GameEntity {

    protected Animation animation;

    /**
     * Constructs an AnimationEntity instance with specified parameters including position, dimension,
     * initial state, and animation dimensions.
     *
     * @param position  The position of the entity on the game screen.
     * @param dimension The dimensions (width and height) of the entity.
     * @param state     The initial state of the entity (e.g., IDLE, MOVING).
     * @author Pardeep Singh Manhas
     */
    public AnimatedGameEntity(Position position, Dimension dimension, EntityStates state, GameImage animationImage) {
        super(position, dimension, animationImage, state);
        this.gameImage = animationImage;
        animation = new Animation(animationImage);
    }

    public abstract void update();

    protected void updateAnimation(int index) {
        animation.update(index);
    }

    protected abstract void updateState();
}