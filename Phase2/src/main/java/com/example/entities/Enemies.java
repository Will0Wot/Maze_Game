package com.example.entities;

import java.awt.Dimension;

import com.example.utils.EntityStates;
import com.example.utils.Position;

/**
 * Enemies class represents abstract enemy entities in the game.
 * This class extends AnimatedGameEntity and provides common functionality for enemy entities.
 * @author Manya Sharma
 */
public abstract class Enemies extends AnimatedGameEntity {

    /**
     * Constructor to create an Enemies object.
     *
     * @param position  The position of the enemy.
     * @param dimension The dimensions of the enemy.
     * @param state     The state of the enemy.
     */
    public Enemies(Position position, Dimension dimension, EntityStates state, GameImage image) {
        super(position, dimension, state, image);

    }

}