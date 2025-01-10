package com.example.utils;

/**
 * The EntityStates enum defines the possible states that an entity within the game can be in. For this game,
 * this includes the IDLE, MOVING, and DEAD states that trigger for each entity based on game conditions.
 *
 * @author Pardeep Singh Manhas
 */
public enum EntityStates {
    IDLE(0),
    MOVING(1),
    DEAD(3);
    private int state;

    private EntityStates(int state) {
        this.state = state;
    }

}
