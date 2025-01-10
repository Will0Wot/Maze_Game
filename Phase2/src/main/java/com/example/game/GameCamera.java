package com.example.game;

import java.awt.*;

/**
 * GameCamera is a class that will render part of the total game board. As the player Entity moves around the playable
 * screen, it will push GameCamera's position around the total board size, thereby rendering new portions of the total board.
 * All objects must be offset by this class's X and Y positions, which themselves must be updated by the player Entity's
 * current position on the board. This offset is required to draw things in their "correct" location, or as they should
 * be found in the game world with no camera present at all. Currently, GameCamera's are rectangles.
 *
 * @author Pardeep Singh Manhas
 */
public class GameCamera {
    private float cameraXPos;
    private float cameraYPos;
    private final float boardWidth;
    private final float boardHeight;
    private final float cameraWidthTriggereable;
    private final float cameraHeightTriggerable;
    private final float screenWidth;
    private final float screenHeight;
    private float rightBoundary;
    private float leftBoundary;
    private float bottomBoundary;
    private float topBoundary;

    /**
     * Constructs a GameCamera instance, which is used to render a portion of the map.
     *
     * @param xPos              - The x (cartesian coordinate) position this instance should be initially drawn to in a nxm grid, where the initial xPoint (0) is the top left corner of the screen.
     * @param yPos              - The y (cartesian coordinate) position this instance should be initially drawn to in a nxm grid, where the initial yPoint (0) is the top left corner of the screen.
     * @param boardScreenWidth  - The largest width that this instance should be. This is automatically scaled down to prevent camera problems.
     * @param boardScreenHeight - The largest height that this instance should be. This is automatically scaled down to prevent camera problems.
     * @param boardWidth        - The maximum width of the world this instance exists in. Used to prevent continuous camera movement.
     * @param boardHeight       - The maximum height of the world this instance exists in. Used to prevent continuous camera movement.
     * @author Pardeep Singh Manhas
     */
    public GameCamera(float xPos, float yPos, float boardScreenWidth, float boardScreenHeight, float boardWidth, float boardHeight) {
        this.screenWidth = boardScreenWidth;
        this.screenHeight = boardScreenHeight;
        this.cameraWidthTriggereable = screenWidth * .66f;
        this.cameraHeightTriggerable = boardScreenHeight * .66f;
        this.cameraXPos = xPos - cameraHeightTriggerable / 2;
        this.cameraYPos = yPos - cameraWidthTriggereable / 2;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.rightBoundary = cameraXPos + (cameraWidthTriggereable) * .5f;
        this.leftBoundary = cameraXPos + (cameraWidthTriggereable) * .5f;
        this.bottomBoundary = cameraYPos + (cameraHeightTriggerable) * .5f;
        this.topBoundary = cameraYPos + (cameraHeightTriggerable) * .5f;
    }

    /**
     * This method is used to adjust this instance's position relative to the Player entity. Once the player entity passes
     * some % total of this instances width, the Camera will be adjusted to the left or right of the game board, causing
     * all elements to be drawn by a new offset, which enables a new portion of the world to be rendered.
     *
     * @param newPlayerXCoordinate - The player's new position in the world.
     * @author Pardeep Singh Manhas
     */
    public void incrementCameraX(float newPlayerXCoordinate) {
        // difference between player position and right boundary.
        float rightDiff = newPlayerXCoordinate - rightBoundary;
        float leftDiff = newPlayerXCoordinate - leftBoundary;
        // if > 0, player has crossed the boundary and is past the right boundary. Move the camera if able to.
        if (rightDiff > 0) {
            // Only move camera if the camera would not go past the board width.
            if (!(screenWidth + cameraXPos + rightDiff >= boardWidth)) {
                rightBoundary += rightDiff;
                leftBoundary += rightDiff;
                cameraXPos += rightDiff;
            }
        }

        if (leftDiff <= 0) {
            if (!(cameraXPos + leftDiff <= 0)) {
                rightBoundary += leftDiff;
                leftBoundary += leftDiff;
                cameraXPos += leftDiff;
            }
        }
    }


    /**
     * This method is used to adjust this instance's position relative to the Player entity. Once the player entity passes
     * some % total of this instances height, the Camera will be adjusted to the top or bottom of the game board, causing
     * all elements to be drawn by a new offset, which enables a new portion of the world to be rendered.
     *
     * @param newPlayerYCoordinate - The player's new position in the world.
     * @author Pardeep Singh Manhas
     */
    public void incrementCameraY(float newPlayerYCoordinate) {
        // difference between player position and right boundary.
        float bottomDiff = newPlayerYCoordinate - bottomBoundary;
        float topDiff = newPlayerYCoordinate - topBoundary;
        // if > 0, player has crossed the boundary and is past the right boundary. Move the camera if able to.
        if (bottomDiff > 0) {
            // Only move camera if the camera would not go past the board width.
            if (!(screenHeight + cameraYPos + bottomDiff >= boardHeight)) {
                bottomBoundary += bottomDiff;
                topBoundary += bottomDiff;
                cameraYPos += bottomDiff;
            }
        }

        if (topDiff <= 0) {
            if (!(cameraYPos + topDiff < 0)) {
                topBoundary += topDiff;
                bottomBoundary += topDiff;
                cameraYPos += topDiff;
            }
        }
    }

    /**
     * Each instance of a GameCamera is essentially a rectangle in a cartesian plane. This method returns the x coordinate of this instance's
     * top left corner.
     *
     * @return The current top left corner of this instance.
     * @author Pardeep Singh Manhas
     */
    public float getCameraXPos() {
        return cameraXPos;
    }

    /**
     * Each instance of a GameCamera is essentially a rectangle in a cartesian plane. This method returns the y coordinate of this instance's
     * top left corner.
     *
     * @return The current top left corner of this instance.
     * @author Pardeep Singh Manhas
     */
    public float getCameraYPos() {
        return cameraYPos;
    }

    /**
     * A test method used for drawing this instance onto a JPanel or some other drawable Java component. Used for Debugging.
     *
     * @param g Graphics component from Swing that draw's the Camera's outline to the board.
     * @author Pardeep Singh Manhas
     */
    public void draw(Graphics g) {
        g.drawRect((int) (cameraXPos - cameraWidthTriggereable / 2), (int) (cameraYPos - cameraHeightTriggerable / 2), (int) cameraWidthTriggereable, (int) cameraHeightTriggerable);
    }
}