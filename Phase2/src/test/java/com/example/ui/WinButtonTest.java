package com.example.ui;

import com.example.gamestate.Gamestate;
import com.example.ui.WinButton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Rectangle;

import static org.junit.jupiter.api.Assertions.*;

class WinButtonTest {
    private WinButton winButton;
    private final int x = 100;
    private final int y = 200;
    private final Gamestate gameState = Gamestate.PLAYING;

    @BeforeEach
    void setUp() {
        winButton = new WinButton(x, y, 0, gameState);
    }

    @Test
    void testButtonInitialization() {
        assertEquals(gameState, winButton.getState(), "Button state should match the initialized game state.");
        assertFalse(winButton.isPressOver(), "Button should not be in pressed state initially.");
        assertNotNull(winButton.getBounds(), "Button bounds should be initialized.");
    }

    @Test
    void testButtonPressOver() {
        winButton.setPressOver(true);
        assertTrue(winButton.isPressOver(), "Button should be in pressed state after being pressed.");
        winButton.resetBools();
        assertFalse(winButton.isPressOver(), "Button should not be in pressed state after reset.");
    }

    @Test
    void testButtonBoundsInitialization() {
        Rectangle expectedBounds = new Rectangle((1200 - x) / 2, (800 - y) / 2, 192, 64);
        assertEquals(expectedBounds, winButton.getBounds(), "Button bounds should be correctly initialized based on its position.");
    }
}
