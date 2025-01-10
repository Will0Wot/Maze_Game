package com.example.entities;

/**
 * An interface for entities that can move within the game.
 * Implementing classes must provide a method to update the position of the entity.
 *
 * @author Manya Sharma
 */
public interface Mover {
    /**
     * Updates the position of the entity.
     * This method should be implemented to handle the movement logic of the entity.
     */
    void updateEntityPosition();
}
