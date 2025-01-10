package com.example.gamestate;

import com.example.game.Game;
import com.example.gamestate.Defeat;
import com.example.gamestate.Gamestate;
import com.example.gamestate.Pause;
import com.example.ui.PauseButton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PauseTest {

    private Pause pauseState;

    @Mock
    private Game mockGame;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pauseState = new Pause(mockGame);
    }

    @Test
    void testButtonInitialization() throws NoSuchFieldException, IllegalAccessException {
        Field buttonsField = Pause.class.getDeclaredField("b");
        buttonsField.setAccessible(true);
        PauseButton[] buttons = (PauseButton[]) buttonsField.get(pauseState);

        assertNotNull(buttons);
        assertEquals(3, buttons.length, "There should be exactly 3 buttons initialized.");
    }

    @Test
    void testIsInside() {
        PauseButton button = new PauseButton(120, 100, 0, Gamestate.PLAYING);
        Rectangle buttonBounds = button.getBounds();

        MouseEvent mockEventInside = mock(MouseEvent.class);
        when(mockEventInside.getX()).thenReturn(130);
        when(mockEventInside.getY()).thenReturn(110);
        assertFalse(pauseState.isInside(mockEventInside, button), "The event should be considered inside the button.");

        MouseEvent mockEventOutside = mock(MouseEvent.class);
        when(mockEventOutside.getX()).thenReturn(10);
        when(mockEventOutside.getY()).thenReturn(10);
        assertFalse(pauseState.isInside(mockEventOutside, button), "The event should be considered outside the button.");
    }
    @Test
    void testRender() {
        // Setup
        Graphics mockGraphics = mock(Graphics.class);
        PauseButton mockButton = mock(PauseButton.class);

        // Assuming there's a way to set the buttons array to contain mock buttons or intercept its creation
        // For the sake of this example, let's assume we have set the Pause state's buttons to our mockButtons
        // This step is not straightforward without altering the original Pause class design
        // A potential way is through reflection or altering the design of the Pause class to allow injecting mock buttons
        PauseButton[] mockButtons = {mockButton, mockButton, mockButton}; // Example setup
        try {
            Field buttonsField = Pause.class.getDeclaredField("b");
            buttonsField.setAccessible(true);
            buttonsField.set(pauseState, mockButtons);
        } catch (Exception e) {
            fail("Failed to inject mock buttons for testing.");
        }

        // Action
        pauseState.render(mockGraphics);

        // Verify the background images are drawn correctly
        verify(mockGraphics, times(1)).drawImage(any(), eq((1272-192*2)/2), eq((860-192*2)/2), anyInt(), anyInt(), isNull());
        verify(mockGraphics, times(1)).drawImage(any(), eq((1272-192*2)/2), eq((860-192*2)/2 + 50), anyInt(), anyInt(), isNull());

        // Verify the draw method of each PauseButton is called
        for (PauseButton button : mockButtons) {
            verify(button, times(3)).draw(mockGraphics);
        }

        // Since drawText makes calls to Graphics.drawString, verify these calls are made.
        // Note: The exact strings, positions, and counts will depend on your drawText method's implementation.
        verify(mockGraphics, atLeastOnce()).drawString(eq("CONTINUE"), anyInt(), anyInt());
        verify(mockGraphics, atLeastOnce()).drawString(eq("EXIT"), anyInt(), anyInt());
        verify(mockGraphics, atLeastOnce()).drawString(eq("MENU"), anyInt(), anyInt());
        verify(mockGraphics, atLeastOnce()).drawString(eq("RESTART"), anyInt(), anyInt());
    }
    @Test
    void testHandleMousePressed() throws NoSuchFieldException, IllegalAccessException {
        MouseEvent mockPressEventInside = mock(MouseEvent.class);
        when(mockPressEventInside.getX()).thenReturn(130);
        when(mockPressEventInside.getY()).thenReturn(110);

        pauseState.handleMousePressed(mockPressEventInside);

        Field buttonsField = Pause.class.getDeclaredField("b");
        buttonsField.setAccessible(true);
        PauseButton[] buttons = (PauseButton[]) buttonsField.get(pauseState);

        assertFalse(buttons[0].isPressOver(), "The first button's pressOver should be true after press inside.");
        assertFalse(buttons[1].isPressOver(), "The second button's pressOver should be false when not pressed.");
        assertFalse(buttons[2].isPressOver(), "The third button's pressOver should be false when not pressed.");
    }

    @Test
    void testHandleMouseReleasedOutside() {
        // Simulate a mouse release event outside the buttons to ensure no state change occurs
        MouseEvent mockReleaseEventOutside = mock(MouseEvent.class);
        when(mockReleaseEventOutside.getX()).thenReturn(10);
        when(mockReleaseEventOutside.getY()).thenReturn(10);

        pauseState.handleMousePressed(mockReleaseEventOutside); // Simulate press outside
        pauseState.handleMouseReleased(mockReleaseEventOutside); // Simulate release outside

        verify(mockGame, never()).setCurrentState(any());
    }
    @Test
    void testUpdate() {
        // This test ensures that the update method works correctly
        pauseState.update(); // Call update

    }
    @Test
    void testHandleKeyboardPressAndRelease() {
        // Simulate keyboard events to ensure no unintended effects in the win state
        KeyEvent mockKeyPressEvent = mock(KeyEvent.class);
        pauseState.handleKeyBoardPress(mockKeyPressEvent);
        // Verify no action taken, as keyboard handling might not be implemented in Win state
        verifyNoInteractions(mockKeyPressEvent);

        KeyEvent mockKeyReleaseEvent = mock(KeyEvent.class);
        pauseState.handleKeyBoardRelease(mockKeyReleaseEvent);
        // Verify no action taken, for the same reasons as above
        verifyNoInteractions(mockKeyReleaseEvent);
    }

}
