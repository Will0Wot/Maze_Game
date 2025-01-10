package com.example.game;

import javax.swing.*;
import java.awt.*;


/**
 * Represents the playable area of the game "Knight's Quest". This class extends and is responsible for initializing the size of the game
 * This class holds a reference to the Game instance it belongs to and delegates rendering of objects to said Game class/object. Extends JPanel.
 *
 * @author Pardeep Singh Manhas
 */
public class GameScreen extends JPanel {
    private final Game game;

    /**
     * Constructs a new GameScreen with the specified width and height, and associates it with the given Game instance. The
     * width and height of the screen should be proportional to the game board's width and height for consistency purposes.
     *
     * @param width  The width of the game screen in pixels.
     * @param height The height of the game screen in pixels.
     * @param game   The Game instance to associate with this instance, for the purposes of rendering.
     * @author Pardeep Singh Manhas
     */
    public GameScreen(int width, int height, Game game) {
        this.game = game;
        this.setPreferredSize(new Dimension(width, height));
    }

    /**
     * Overrides the painComponent method of JPanel to perform custom rendering
     * of the game's graphical elements. Called whenever the screen must be redrawn.
     * Utilizes this instance's Game object to render Entities to the board.
     *
     * @param g The Graphics object provided by Swing that represents the drawing context
     *          of the component.
     * @author Pardeep Singh Manhas
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    /**
     * Retrieves the Game instance associated with this instance.
     * This method allows other parts of the application to access the current instance's Game object in order to help
     * with rendering.
     *
     * @return The Game instance currently associated with this game screen.
     * @author Pardeep Singh Manhas
     */
    public Game getGame() {
        return game;
    }
}
