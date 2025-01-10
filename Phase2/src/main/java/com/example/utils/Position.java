package com.example.utils;

/**
 * The Position class represents a two-dimensional position within the game. It encapsulates two cartesian coordinates
 * that define some point within a 2D space.
 *
 * @author Pardeep Singh Manhas
 */

public class Position {
    private float x;
    private float y;

    /**
     * Initializes a new Position instance at the point (0, 0).
     *
     * @author Pardeep Singh Manhas
     */
    public Position() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Initializes a new Position instance. The x and y parameters correspond to positions in a cartesian
     * plane for a 2D space.
     *
     * @param x: A floating pointer number that represents the x-axis of the 2D space.
     * @param y: A floating pointer number that represents the y-axis of the 2D space.
     * @author Pardeep Singh Manhas
     */
    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Return the column/x coordinate of this Position instance.
     *
     * @return The x coordinate of this Position instance as a floating point number.
     * @author Pardeep Singh Manhas
     */
    public float getX() {
        return x;
    }

    /**
     * Return the row/y coordinate of this Position instance.
     *
     * @return The y coordinate of this Position instance as a floating point number.
     * @author Pardeep Singh Manhas
     */
    public float getY() {
        return y;
    }

    /**
     * Modifies the column/x coordinate of this Position instance to the provided float parameter.
     *
     * @param x The new row/x coordinate of this instance.
     * @author Pardeep Singh Manhas
     */
    public void setX(float x) {
        this.x = x;
    }


    /**
     * Modifies the row/y coordinate of this Position instance to the provided float parameter.
     *
     * @param y The new column/y coordinate of this instance.
     * @author Pardeep Singh Manhas
     */
    public void setY(float y) {
        this.y = y;
    }
}