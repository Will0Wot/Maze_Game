package com.example.gamestate;

import com.example.game.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * The State class represents a mode that the current game could find itself in. For example, it could be in the
 * playing mode, meaning that some user is currently playing the game, a pause mode indicating some suspension of the
 * game, and so on. Must be provided methods on how to handle user input, how to update the screen, and how to update
 * its mode's logic. A Game class is provided to facilitate communication between the State and other components
 * associated with the Game.
 *
 * @author Pardeep Manhas
 */
public abstract class State {
    private Game game;

    public State(Game game) {
        this.game = game;
    }

    /**
     * Returns the game associated with the current state.
     *
     * @return Game: A Game instance.
     * @author Pardeep Manhas
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Render paints the state to the screen associated with the Game. Only one state me be rendered at a time.
     *
     * @param g : A graphics object that is used to paint all game components.
     * @author Pardeep Manhas
     */
    public abstract void render(Graphics g);


    /**
     * Updates the internal logic of the state.
     */
    public abstract void update();

    /**
     * Handles keyboard inputs from the user in the context of the current state. Not all listeners are utilized in all
     * states.
     *
     * @author Pardep Manhas
     * @param e A keyEvent that can be translated to some action within the current state context.
     */
    public abstract void handleKeyBoardPress(KeyEvent e);


    /**
     * Handles keyboard releases from the user in the context of the current state. Not all listeners are utilized in all
     * states.
     *
     * @author Pardep Manhas
     * @param e A keyEvent that can be translated to some action within the current state context.
     */
    public abstract void handleKeyBoardRelease(KeyEvent e);


    /**
     * Handles mouse inputs from the user in the context of the current state. Not all listener types are utilized in all
     * states.
     *
     * @author Pardep Manhas
     * @param e A keyEvent that can be translated to some action within the current state context.
     */

    public abstract void handleMouseInput(MouseEvent e);

    public abstract void handleMousePressed(MouseEvent e);
    public abstract void handleMouseReleased(MouseEvent e);
    //public abstract void handleMouseMoved(MouseEvent e);

}
