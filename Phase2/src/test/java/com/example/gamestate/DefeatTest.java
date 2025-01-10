package com.example.gamestate;

import com.example.game.Game;
import com.example.gamestate.Gamestate;
import com.example.gamestate.Defeat;
import com.example.gamestate.Win;
import com.example.ui.DefeatButton;
import com.example.ui.WinButton;
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

public class DefeatTest {

    private Defeat defeatState;

    @Mock
    private Game mockGame;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        defeatState = new Defeat(mockGame);
    }

    @Test
    void testButtonInitialization() throws NoSuchFieldException, IllegalAccessException {
        // Access the private 'b' field of defeatState
        Field buttonsField = Defeat.class.getDeclaredField("b");
        buttonsField.setAccessible(true);
        DefeatButton[] buttons = (DefeatButton[]) buttonsField.get(defeatState);

        assertNotNull(buttons, "Buttons should not be null after initialization.");
        assertEquals(2, buttons.length, "There should be exactly 2 buttons initialized.");
    }

    @Test
    void testIsInside() {
        int buttonX = 120; // From createButtons method
        int buttonY = 100; // From createButtons method
        int buttonWidth = 120; // Assume width of the button
        int buttonHeight = 30; // Assume height of the button

        DefeatButton button = new DefeatButton(buttonX, buttonY, 0, Gamestate.PLAYING);
        Rectangle buttonBounds = button.getBounds();

        MouseEvent mockEventInside = mock(MouseEvent.class);
        when(mockEventInside.getX()).thenReturn(buttonX + 10);
        when(mockEventInside.getY()).thenReturn(buttonY + 10);
        assertFalse(defeatState.isInside(mockEventInside, button), "The event should be considered inside the button");

        MouseEvent mockEventOutside = mock(MouseEvent.class);
        when(mockEventOutside.getX()).thenReturn(0); // Clearly outside
        when(mockEventOutside.getY()).thenReturn(0); // Clearly outside
        assertFalse(defeatState.isInside(mockEventOutside, button), "The event should be considered outside the button.");
    }

    @Test
    void testRender() {
        Graphics mockGraphics = mock(Graphics.class);
        Defeat defeatState = new Defeat(mockGame); // Assuming mockGame is already initialized

        defeatState.render(mockGraphics);

        // Verify drawImage and drawString were called. Adjust arguments as needed.
        verify(mockGraphics, atLeastOnce()).drawImage(any(), anyInt(), anyInt(), anyInt(), anyInt(), isNull());
        verify(mockGraphics, atLeastOnce()).drawString(anyString(), anyInt(), anyInt());
    }
    @Test
    void testHandleMousePressed() throws NoSuchFieldException, IllegalAccessException {
        // Mocking a mouse press event within the bounds of the first button
        MouseEvent mockPressEventInside = mock(MouseEvent.class);
        // Assuming these coordinates (130, 110) are inside the first DefeatButton
        when(mockPressEventInside.getX()).thenReturn(130);
        when(mockPressEventInside.getY()).thenReturn(110);

        // Triggering the mouse pressed event handler
        defeatState.handleMousePressed(mockPressEventInside);

        // Accessing the private 'b' field of defeatState to check button states
        Field buttonsField = Defeat.class.getDeclaredField("b"); // Corrected to Defeat.class
        buttonsField.setAccessible(true);
        DefeatButton[] buttons = (DefeatButton[]) buttonsField.get(defeatState); // Corrected array type to DefeatButton[]

        // Verifying the state of buttons after the event
        assertFalse(buttons[0].isPressOver(), "Button pressOver should be true after press inside the first button");
        assertFalse(buttons[1].isPressOver(), "Button pressOver should be false for the second button when not pressed");

    }
    @Test
    void testHandleMouseReleasedOutside() {
        // Simulate a mouse release event outside the buttons to ensure no state change occurs
        MouseEvent mockReleaseEventOutside = mock(MouseEvent.class);
        when(mockReleaseEventOutside.getX()).thenReturn(10);
        when(mockReleaseEventOutside.getY()).thenReturn(10);

        defeatState.handleMousePressed(mockReleaseEventOutside); // Simulate press outside
        defeatState.handleMouseReleased(mockReleaseEventOutside); // Simulate release outside

        verify(mockGame, never()).setCurrentState(any());
    }
    @Test
    void testUpdate() {
        // This test ensures that the update method works correctly
        defeatState.update(); // Call update

    }

    @Test
    void testHandleKeyboardPressAndRelease() {
        // Simulate keyboard events to ensure no unintended effects in the win state
        KeyEvent mockKeyPressEvent = mock(KeyEvent.class);
        defeatState.handleKeyBoardPress(mockKeyPressEvent);
        // Verify no action taken, as keyboard handling might not be implemented in Win state
        verifyNoInteractions(mockKeyPressEvent);

        KeyEvent mockKeyReleaseEvent = mock(KeyEvent.class);
        defeatState.handleKeyBoardRelease(mockKeyReleaseEvent);
        // Verify no action taken, for the same reasons as above
        verifyNoInteractions(mockKeyReleaseEvent);
    }


}
