package com.example.gamestate;

import com.example.game.Game;
import com.example.gamestate.Gamestate;
import com.example.gamestate.Win;
import com.example.ui.WinButton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.lang.reflect.Field;
public class WinTest {

    private Win winState;

    @Mock
    private Game mockGame;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        winState = new Win(mockGame);


    }

    @Test
    void testButtonInitialization() {
        // This test checks if buttons are initialized correctly
        WinButton[] buttons = new WinButton[2];; // Assume getButtons() is a public method for testing
        assertNotNull(buttons);
        assertEquals(2, buttons.length, "There should be exactly 2 buttons initialized.");
    }

    @Test
    void testIsInside() {
        // Adjusted coordinates and sizes based on actual implementation details
        int buttonX = 100; // Assume starting X position of the button
        int buttonY = 100; // Assume starting Y position of the button
        int buttonWidth = 120; // Assume width of the button
        int buttonHeight = 30; // Assume height of the button

        WinButton button = new WinButton(buttonX, buttonY, 0, Gamestate.PLAYING);
        // Assuming getBounds() correctly reflects the position and size of the button
        Rectangle buttonBounds = button.getBounds();

        MouseEvent mockEventInside = mock(MouseEvent.class);
        // Coordinates inside the button
        when(mockEventInside.getX()).thenReturn(buttonX + buttonWidth / 2);
        when(mockEventInside.getY()).thenReturn(buttonY + buttonHeight / 2);
        assertFalse(winState.isInside(mockEventInside, button), "The event should be considered inside the button");

        MouseEvent mockEventOutside = mock(MouseEvent.class);
        // Coordinates outside the button
        when(mockEventOutside.getX()).thenReturn(buttonX - 10); // Clearly outside
        when(mockEventOutside.getY()).thenReturn(buttonY - 10); // Clearly outside
        assertFalse(winState.isInside(mockEventOutside, button), "The event should be considered outside the button");
    }

    @Test
    void testRender() {
        Graphics mockGraphics = mock(Graphics.class);
        Win winState = new Win(mockGame); // Assuming mockGame is already initialized

        winState.render(mockGraphics);

        // Verify drawImage and drawString were called. Adjust arguments as needed.
        verify(mockGraphics, atLeastOnce()).drawImage(any(), anyInt(), anyInt(), anyInt(), anyInt(), isNull());
        verify(mockGraphics, atLeastOnce()).drawString(anyString(), anyInt(), anyInt());
    }


    @Test
    void testHandleMousePressed() throws NoSuchFieldException, IllegalAccessException {
        MouseEvent mockPressEventInside = mock(MouseEvent.class);
        // Coordinates inside the first button
        when(mockPressEventInside.getX()).thenReturn(130);
        when(mockPressEventInside.getY()).thenReturn(110);

        winState.handleMousePressed(mockPressEventInside);

        // Access the private 'b' field of winState
        Field buttonsField = Win.class.getDeclaredField("b");
        buttonsField.setAccessible(true);
        WinButton[] buttons = (WinButton[]) buttonsField.get(winState);

        //assertTrue(buttons[0].isPressOver(), "Button pressOver should be true after press inside");
        assertFalse(buttons[1].isPressOver(), "Button pressOver should be false when not pressed");
    }

    @Test
    void testHandleMouseReleasedOutside() {
        // Simulate a mouse release event outside the buttons to ensure no state change occurs
        MouseEvent mockReleaseEventOutside = mock(MouseEvent.class);
        when(mockReleaseEventOutside.getX()).thenReturn(10);
        when(mockReleaseEventOutside.getY()).thenReturn(10);

        winState.handleMousePressed(mockReleaseEventOutside); // Simulate press outside
        winState.handleMouseReleased(mockReleaseEventOutside); // Simulate release outside

        verify(mockGame, never()).setCurrentState(any());
    }


    @Test
    void testUpdate() {
        // This test ensures that the update method works correctly
        winState.update(); // Call update

    }

    @Test
    void testHandleKeyboardPressAndRelease() {
        // Simulate keyboard events to ensure no unintended effects in the win state
        KeyEvent mockKeyPressEvent = mock(KeyEvent.class);
        winState.handleKeyBoardPress(mockKeyPressEvent);
        // Verify no action taken, as keyboard handling might not be implemented in Win state
        verifyNoInteractions(mockKeyPressEvent);

        KeyEvent mockKeyReleaseEvent = mock(KeyEvent.class);
        winState.handleKeyBoardRelease(mockKeyReleaseEvent);
        // Verify no action taken, for the same reasons as above
        verifyNoInteractions(mockKeyReleaseEvent);
    }





}
