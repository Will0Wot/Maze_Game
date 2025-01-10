package com.example.utils;

/**
 * The Directions enum defines the cardinal directions to facilitate movement
 * and animations when applicable. It provides a standardized way of representing
 * directions for entities within the game.
 *
 * @author Pardeep Singh Manhas
 */
public enum Directions {
    LEFT(0),
    UP(1),
    RIGHT(2),
    DOWN(3),
    NONE(3);
    private int direction = 0;

    Directions(int direction) {
        this.direction = direction;
    }
}
