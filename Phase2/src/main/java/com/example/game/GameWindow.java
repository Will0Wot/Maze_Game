package com.example.game;

import javax.swing.*;

/**
 * Class that represents the game window. Most operations on it should be performed in a Game class. No inherent size
 * is excepted for the window. Instead, it relies on the size of its subcomponents and panels.
 */
public class GameWindow extends JFrame {
    public GameWindow() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }
}
