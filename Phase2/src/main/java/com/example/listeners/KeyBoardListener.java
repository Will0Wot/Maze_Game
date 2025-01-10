package com.example.listeners;

import com.example.game.Game;
import com.example.gamestate.Gamestate;
import com.example.utils.Directions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The KeyboardListener class is responsible for capturing keyboard input from the user for the purpose of facilitating
 * player movement and UI navigation based on the current game state.
 *
 * @author Pardeep Manhas
 */

public class KeyBoardListener implements KeyListener {
    private Game game;

    public KeyBoardListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
//                if (game.isCurrentState(Gamestate.PLAYING)){
//                    //game.setCurrentState(Gamestate.MENU);
//                    game.setCurrentState(Gamestate.PAUSE);
//                } else {
//                    game.setCurrentState(Gamestate.PLAYING);
//                }
//                break;
                // Check if the current state is PLAYING, then switch to PAUSE
                if (game.isCurrentState(Gamestate.PLAYING)){
                    game.setCurrentState(Gamestate.PAUSE);
                }
                // Check if the current state is PAUSE, then switch back to PLAYING
                else if (game.isCurrentState(Gamestate.PAUSE)){
                    game.setCurrentState(Gamestate.PLAYING);
                }
                break;
            default:
                game.handleKeyBoardPress(e);
                break;
//
//            case KeyEvent.VK_ESCAPE -> game.toggleMenuState();
//            default -> game.handleKeyBoardPress(e);

        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        game.handleKeyBoardRelease(e);
    }
}
