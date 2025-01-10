package com.example.entities;

/**
 * An interface for entities that can be interacted with in the game.
 * Implementing classes must provide methods to interact with other entities and retrieve interaction details.
 *
 * @author Manya Sharma
 */
public interface Interactable {
    /**
     * Defines the interaction behavior between this entity and a Knight entity.
     *
     * @param knight The Knight entity with which interaction occurs.
     */
    void interact(Knight knight);

    /**
     * Retrieves the amount associated with the interaction.
     * This could represent a reward, penalty, or any other relevant value.
     *
     * @return The amount associated with the interaction.
     */
    int getInteractableAmount();
}
