package com.example.ui;

import com.example.gamestate.Gamestate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Rectangle;
import static org.junit.jupiter.api.Assertions.*;

class DefeatButtonTest {
    private DefeatButton defeatButton;
    private final int x = 200;
    private final int y = 300;
    private final Gamestate gameState = Gamestate.DEFEAT; // Assuming Gamestate includes a DEFEAT state

    @BeforeEach
    void setUp() {
        // Initialize DefeatButton with sample values
        defeatButton = new DefeatButton(x, y, 1, gameState);
    }

    @Test
    void testButtonInitialization() {
        // Verify the button's state and press conditions are initialized correctly
        assertEquals(gameState, defeatButton.getState(), "The button's game state should match the one provided at initialization.");
        assertFalse(defeatButton.isPressOver(), "Initially, the button should not be in a pressed state.");
        assertNotNull(defeatButton.getBounds(), "Button bounds should be initialized.");
    }

    @Test
    void testPressOverState() {
        // Simulate pressing the button
        defeatButton.setPressOver(true);
        assertTrue(defeatButton.isPressOver(), "The button should be in a pressed state after setPressOver(true) is called.");

        // Simulate releasing the button
        defeatButton.setPressOver(false);
        assertFalse(defeatButton.isPressOver(), "The button should not be in a pressed state after setPressOver(false) is called.");
    }

    @Test
    void testResetBools() {
        // Set the button to a pressed state, then reset it
        defeatButton.setPressOver(true);
        defeatButton.resetBools();

        assertFalse(defeatButton.isPressOver(), "After calling resetBools, the button's pressed state should be reset to false.");
        // The notPressOver state check would be added here if it were used in the update logic or had getters/setters
    }

    @Test
    void testButtonBoundsInitialization() {
        // Verify the button's bounds are initialized correctly based on its position
        Rectangle expectedBounds = new Rectangle((1200 - x) / 2, (800 - y) / 2, 192, 64);
        assertEquals(expectedBounds, defeatButton.getBounds(), "The bounds of the button should be correctly initialized based on its position.");
    }


}
