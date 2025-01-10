package com.example.ui;

import com.example.gamestate.Gamestate;
import com.example.ui.PauseButton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Rectangle;
import static org.junit.jupiter.api.Assertions.*;

class PauseButtonTest {
    private PauseButton pauseButton;
    private final int x = 100;
    private final int y = 200;
    private final Gamestate gameState = Gamestate.PAUSE; // Assuming Gamestate includes a PAUSED state

    @BeforeEach
    void setUp() {
        pauseButton = new PauseButton(x, y, 0, gameState);
    }

    @Test
    void testButtonInitialization() {
        assertEquals(gameState, pauseButton.getState(), "Button state should match the initialized game state.");
        assertFalse(pauseButton.isPressOver(), "Button should not be in pressed state initially.");
        // Check for non-null bounds as a proxy for successful bounds initialization
        assertNotNull(pauseButton.getBounds(), "Button bounds should be initialized.");
    }

    @Test
    void testButtonPressOver() {
        // Simulate button press
        pauseButton.setPressOver(true);
        assertTrue(pauseButton.isPressOver(), "Button should be in pressed state after being pressed.");

        // Simulate button release
        pauseButton.setPressOver(false);
        assertFalse(pauseButton.isPressOver(), "Button should not be in pressed state after being released.");
    }

    @Test
    void testResetBools() {
        // Set the state to pressed, then reset
        pauseButton.setPressOver(true);
        pauseButton.resetBools();

        // Verify both pressOver and notPressOver are reset
        assertFalse(pauseButton.isPressOver(), "Button should not be in pressed state after resetBools.");
        // The notPressOver state check would be added here if it were used in the update logic or had getters/setters
    }

    @Test
    void testButtonBounds() {
        Rectangle expectedBounds = new Rectangle((1200 - x) / 2, (800 - y) / 2, 192, 64);
        assertEquals(expectedBounds, pauseButton.getBounds(), "Button bounds should be correctly initialized based on its position.");
    }


}
