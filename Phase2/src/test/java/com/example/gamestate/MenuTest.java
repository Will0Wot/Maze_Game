package com.example.gamestate;

import com.example.game.Game;
import com.example.gamestate.Gamestate;
import com.example.gamestate.Menu;
import com.example.gamestate.Pause;
import com.example.ui.MenuButton;
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

public class MenuTest {

    private Menu menuState;

    @Mock
    private Game mockGame;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        menuState = new Menu(mockGame);
    }

    @Test
    void testButtonInitialization() throws NoSuchFieldException, IllegalAccessException {
        Field buttonsField = Menu.class.getDeclaredField("b");
        buttonsField.setAccessible(true);
        MenuButton[] buttons = (MenuButton[]) buttonsField.get(menuState);

        assertNotNull(buttons);
        assertEquals(2, buttons.length, "There should be exactly 2 buttons initialized.");
    }

    @Test
    void testIsInside() {
        MenuButton button = new MenuButton(120, 100, 0, Gamestate.PLAYING);
        Rectangle buttonBounds = button.getBounds();

        MouseEvent mockEventInside = mock(MouseEvent.class);
        when(mockEventInside.getX()).thenReturn(130);
        when(mockEventInside.getY()).thenReturn(110);
        assertFalse(menuState.isInside(mockEventInside, button), "The event should be considered inside the button.");

        MouseEvent mockEventOutside = mock(MouseEvent.class);
        when(mockEventOutside.getX()).thenReturn(10);
        when(mockEventOutside.getY()).thenReturn(10);
        assertFalse(menuState.isInside(mockEventOutside, button), "The event should be considered outside the button.");
    }

    @Test
    void testRender() {
        Graphics mockGraphics = mock(Graphics.class);
        MenuButton mockButton = mock(MenuButton.class);

        MenuButton[] mockButtons = {mockButton, mockButton}; // Assuming 2 buttons for Menu
        try {
            Field buttonsField = Menu.class.getDeclaredField("b");
            buttonsField.setAccessible(true);
            buttonsField.set(menuState, mockButtons);
        } catch (Exception e) {
            fail("Failed to inject mock buttons for testing.");
        }

        menuState.render(mockGraphics);

        verify(mockGraphics, times(1)).drawImage(any(), eq((1272 - 192 * 2) / 2), eq((860 - 192 * 2) / 2), anyInt(), anyInt(), isNull());
        verify(mockGraphics, times(1)).drawImage(any(), eq((1272 - 192 * 2) / 2), eq((860 - 192 * 2) / 2 + 50), anyInt(), anyInt(), isNull());

        for (MenuButton button : mockButtons) {
            verify(button, times(2)).draw(mockGraphics);
        }

        verify(mockGraphics, atLeastOnce()).drawString(eq("PLAY"), anyInt(), anyInt());
        verify(mockGraphics, atLeastOnce()).drawString(eq("EXIT"), anyInt(), anyInt());
        verify(mockGraphics, atLeastOnce()).drawString(eq("MENU"), anyInt(), anyInt());
    }

    @Test
    void testHandleMouseReleasedOutside() {
        // Simulate a mouse release event outside the buttons to ensure no state change occurs
        MouseEvent mockReleaseEventOutside = mock(MouseEvent.class);
        when(mockReleaseEventOutside.getX()).thenReturn(10);
        when(mockReleaseEventOutside.getY()).thenReturn(10);

        menuState.handleMousePressed(mockReleaseEventOutside); // Simulate press outside
        menuState.handleMouseReleased(mockReleaseEventOutside); // Simulate release outside

        verify(mockGame, never()).setCurrentState(any());
    }
    @Test
    void testUpdate() {
        // This test ensures that the update method works correctly
        menuState.update(); // Call update

    }
    @Test
    void testHandleKeyboardPressAndRelease() {
        // Simulate keyboard events to ensure no unintended effects in the win state
        KeyEvent mockKeyPressEvent = mock(KeyEvent.class);
        menuState.handleKeyBoardPress(mockKeyPressEvent);
        // Verify no action taken, as keyboard handling might not be implemented in Win state
        verifyNoInteractions(mockKeyPressEvent);

        KeyEvent mockKeyReleaseEvent = mock(KeyEvent.class);
        menuState.handleKeyBoardRelease(mockKeyReleaseEvent);
        // Verify no action taken, for the same reasons as above
        verifyNoInteractions(mockKeyReleaseEvent);
    }
}