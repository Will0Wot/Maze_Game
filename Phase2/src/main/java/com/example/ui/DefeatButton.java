package com.example.ui;

import com.example.gamestate.Gamestate;
import com.example.utils.ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Represents a button specifically designed for defeat scenarios within the game.
 * This button can display different visuals based on its state, such as default or pressed,
 * and is intended to be used in game states that represent a loss or defeat.
 * <p>
 * The button supports custom positioning, dynamic visual updates based on interaction,
 * and integration with game state management.
 * </p>
 */
public class DefeatButton {
    private static final int SCREEN_WIDTH = 1200;
    private static final int SCREEN_HEIGHT = 800;
    private static final int BUTTON_WIDTH = 192;
    private static final int BUTTON_HEIGHT = 64;

    private int x, y, row;
    private Gamestate state;
    private BufferedImage[] imgs = new BufferedImage[3];
    private boolean pressOver;
    private Rectangle bounds;
    private int index;
    /**
     * Constructs a new DefeatButton with specified parameters, initializing its visuals
     * and interaction bounds.
     *
     * @param x     The x-coordinate for the button's position.
     * @param y     The y-coordinate for the button's position.
     * @param row   The row index for selecting specific visuals or effects.
     * @param state The current game state associated with this button.
     */
    public DefeatButton(int x, int y, int row, Gamestate state) {
        this.x = x;
        this.y = y;
        this.row = row;
        this.state = state;
        loadImages();
        initBounds();
    }

    private void initBounds() {
        int centerX = (SCREEN_WIDTH - x) / 2;
        int centerY = (SCREEN_HEIGHT - y) / 2;
        bounds = new Rectangle(centerX, centerY, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private void loadImages() {
        try {
            imgs[0] = ResourceLoader.getImage(ResourceLoader.BUTTON_RED);
            imgs[1] = ResourceLoader.getImage(ResourceLoader.BUTTON_REDPRESSED);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load button images: " + e.getMessage(), e);
        }
    }
    /**
     * Draws the button on the provided Graphics context, displaying the current state's visual.
     *
     * @param g The Graphics context to draw on.
     */
    public void draw(Graphics g) {
        BufferedImage currentImage = imgs[index];
        int centerX = (SCREEN_WIDTH - x) / 2;
        int centerY = (SCREEN_HEIGHT - y) / 2;
        g.drawImage(currentImage, centerX, centerY, currentImage.getWidth(), currentImage.getHeight(), null);
    }

    public void update() {
        index = pressOver ? 1 : 0;
    }

    public boolean isPressOver() {
        return pressOver;
    }

    public void setPressOver(boolean pressOver) {
        this.pressOver = pressOver;
        update();
    }

    public void resetBools() {
        pressOver = false;
        update();
    }

    public Gamestate getState() {
        return state;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
