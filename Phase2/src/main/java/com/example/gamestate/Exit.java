package com.example.gamestate;

import com.example.game.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
/**
 * Represents the state of the game when exiting.
 * This class provides the framework for handling the game's exit process, though the actual
 * quitting mechanism is to be implemented as needed. It extends the {@link State} class,
 * inheriting its basic structure while providing specific functionalities for the exit process.
 *
 * @author Tom
 */
public class Exit extends State{
    public Exit(Game game) {
        super(game);
        //quitGame();
    }

//    private void quitGame() {
//        System.exit(0);
//    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void update() {

    }

    @Override
    public void handleKeyBoardPress(KeyEvent e) {

    }

    @Override
    public void handleKeyBoardRelease(KeyEvent e) {

    }

    @Override
    public void handleMouseInput(MouseEvent e) {

    }

    @Override
    public void handleMousePressed(MouseEvent e) {

    }

    @Override
    public void handleMouseReleased(MouseEvent e) {

    }
}
