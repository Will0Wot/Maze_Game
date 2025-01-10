package com.example.listeners;

import com.example.game.Game;
import java.awt.event.MouseEvent;

/**
 * The MouseListener class is responsible for capturing mouse input from the user for the purpose of facilitating
 * UI navigation based on the current game state.
 *
 * @author Pardeep Manhas
 */

public class MouseListener implements java.awt.event.MouseListener{

    private Game game;

    public MouseListener(Game game){
        this.game = game;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        //game.toggleMenuState();
        //System.out.println("Clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        game.handleMousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        game.handleMouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
