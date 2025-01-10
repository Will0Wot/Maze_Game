package com.example.listeners;

import com.example.game.Game;
import com.example.listeners.MouseListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.event.MouseEvent;

import static org.mockito.Mockito.*;

class MouseListenerTest {

    @Mock
    private Game mockGame;

    @Mock
    private MouseEvent mockEvent;

    private MouseListener mouseListener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mouseListener = new MouseListener(mockGame);
    }

    @Test
    void testMousePressed() {
        mouseListener.mousePressed(mockEvent);
        verify(mockGame, times(1)).handleMousePressed(mockEvent);
    }

    @Test
    void testMouseReleased() {
        mouseListener.mouseReleased(mockEvent);
        verify(mockGame, times(1)).handleMouseReleased(mockEvent);
    }

    @Test
    void testMouseClicked() {
        // Assuming mouseClicked might have some implementation in the future
        mouseListener.mouseClicked(mockEvent);
        // Since mouseClicked() does nothing (for now), we don't expect any interaction with mockGame
        verifyNoInteractions(mockGame);
    }

    @Test
    void testMouseEntered() {
        // Mouse entered can be tested similarly, assuming it might be implemented later
        mouseListener.mouseEntered(mockEvent);
        // Verify no interaction if the method does nothing
        verifyNoInteractions(mockGame);
    }

    @Test
    void testMouseExited() {
        // Mouse exited can be tested similarly, assuming it might be implemented later
        mouseListener.mouseExited(mockEvent);
        // Verify no interaction if the method does nothing
        verifyNoInteractions(mockGame);
    }
}
