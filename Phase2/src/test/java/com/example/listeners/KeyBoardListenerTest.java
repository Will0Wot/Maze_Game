package com.example.listeners;

import com.example.game.Game;
import com.example.listeners.KeyBoardListener;
import com.example.gamestate.Gamestate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.Component;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class KeyBoardListenerTest {

    @Mock
    private Game mockGame;

    @Mock
    private Component mockComponent; // Mocked component

    private KeyBoardListener keyboardListener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        keyboardListener = new KeyBoardListener(mockGame);
    }

    @Test
    void testKeyPressedEscapeWhilePlaying() {
        // Simulate game is in PLAYING state
        when(mockGame.isCurrentState(Gamestate.PLAYING)).thenReturn(true);

        // Create a KeyEvent with the mocked Component
        KeyEvent escPressed = new KeyEvent(mockComponent, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, KeyEvent.CHAR_UNDEFINED);
        keyboardListener.keyPressed(escPressed);

        // Verify the game state changes to PAUSE
        verify(mockGame, times(1)).setCurrentState(Gamestate.PAUSE);
    }

    @Test
    void testKeyPressedEscapeWhilePaused() {
        // Simulate game is in PAUSE state
        when(mockGame.isCurrentState(Gamestate.PAUSE)).thenReturn(true);

        // Create a KeyEvent with the mocked Component
        KeyEvent escPressed = new KeyEvent(mockComponent, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, KeyEvent.CHAR_UNDEFINED);
        keyboardListener.keyPressed(escPressed);

        // Verify the game state changes back to PLAYING
        verify(mockGame, times(1)).setCurrentState(Gamestate.PLAYING);
    }

    @Test
    void testKeyReleased() {
        // Create a KeyEvent with the mocked Component
        KeyEvent anyKeyReleased = new KeyEvent(mockComponent, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        keyboardListener.keyReleased(anyKeyReleased);

        // Verify the key release is handled by the game
        verify(mockGame, times(1)).handleKeyBoardRelease(anyKeyReleased);
    }
    @Test
    void testKeyPressedOtherThanEscape() {
        // Simulate pressing the 'A' key
        KeyEvent keyEventA = new KeyEvent(mockComponent, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        keyboardListener.keyPressed(keyEventA);

        // Verify the game's handleKeyBoardPress method is called
        verify(mockGame, times(1)).handleKeyBoardPress(keyEventA);
    }
    @Test
    void testKeyTypedDoesNotThrowException() {
        // Simulate typing the 'A' key
        KeyEvent keyEventA = new KeyEvent(mockComponent, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'A');
        assertDoesNotThrow(() -> keyboardListener.keyTyped(keyEventA));
    }

}
