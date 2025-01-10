package com.example.ui;

import com.example.gamestate.Gamestate;
import com.example.ui.MenuButton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Rectangle;
import static org.junit.jupiter.api.Assertions.*;

class MenuButtonTest {
    private MenuButton menuButton;
    private final int x = 150;
    private final int y = 250;
    private final Gamestate gameState = Gamestate.MENU; // Assuming Gamestate includes a MENU state

    @BeforeEach
    void setUp() {
        menuButton = new MenuButton(x, y, 1, gameState); // Initializing with sample values
    }

    @Test
    void testButtonInitialization() {
        assertEquals(gameState, menuButton.getState(), "The button's game state should match the one provided at initialization.");
        assertFalse(menuButton.isPressOver(), "Initially, the button should not be in a pressed state.");
        assertNotNull(menuButton.getBounds(), "Button bounds should be initialized.");
    }

    @Test
    void testPressOverState() {
        menuButton.setPressOver(true);
        assertTrue(menuButton.isPressOver(), "After setting pressOver to true, isPressOver should return true.");

        menuButton.setPressOver(false);
        assertFalse(menuButton.isPressOver(), "After setting pressOver to false, isPressOver should return false.");
    }

    @Test
    void testResetBools() {
        menuButton.setPressOver(true);
        // Assuming the implementation of notPressOver becomes active with its setters and getters uncommented
        // menuButton.setNotPressOver(true);

        menuButton.resetBools();

        assertFalse(menuButton.isPressOver(), "After calling resetBools, pressOver should be reset to false.");
        // assertFalse(menuButton.isNotPressOver(), "After calling resetBools, notPressOver should be reset to false.");
    }

    @Test
    void testButtonBoundsInitialization() {
        Rectangle expectedBounds = new Rectangle((1200 - x) / 2, (800 - y) / 2, 192, 64);
        assertEquals(expectedBounds, menuButton.getBounds(), "The bounds of the button should be correctly initialized based on its position.");
    }


}
