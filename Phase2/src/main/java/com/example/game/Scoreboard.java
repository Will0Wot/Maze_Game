package com.example.game;

import com.example.utils.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Collections;

/**
 * Scoreboard class represents a JLabel component that displays the current score and max score.
 *
 * @author Manya Sharma
 */
public class Scoreboard extends JLabel {
    private int currentScore; // Current score of the player
    private int collectedMeats; // Maximum score achieved
    private int totalMeats;
    private Font font; // Font used for displaying text

    /**
     * Constructor to initialize the scoreboard with default values.
     */

    public Scoreboard(int totalMeats) {
        super();
        this.currentScore = 0;
        this.collectedMeats = 0;
        this.totalMeats = totalMeats;
        Font temp = ResourceLoader.getFont(ResourceLoader.MEDIEVAL_FONT);
        font = temp.deriveFont(18f);
        font = font.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_MEDIUM));
    }

    /**
     * Method to update the current score.
     *
     * @param newScore The score to be added to the current score.
     */
    public void updateCurrentScore(int newScore) {
        this.currentScore += newScore;
    }

    /**
     * Method to update the meat count.
     */
    public void incrementMeatCount() {
        this.collectedMeats++;
    }


    /**
     * Method to get the current score.
     *
     * @return The current score.
     */
    public int getCurrentScore() {
        return currentScore;
    }

    /**
     * Method to get the max score.
     *
     * @return The maximum score achieved.
     */
    public int getCurrentMeatCount() {
        return collectedMeats;
    }

    /**
     * Method to reset the scores.
     * Resets both the current score and the max score to zero.
     */

    public void resetScores(int maxMeats) {
        this.currentScore = 0;
        this.collectedMeats = 0;
        this.totalMeats = maxMeats;
    }

    /**
     * Method to render the scoreboard.
     *
     * @param g        Graphics object used for rendering.
     * @param x_offset Horizontal offset for rendering.
     * @param y_offset Vertical offset for rendering.
     */
    public void render(Graphics g, float x_offset, float y_offset) {
        String maxScoreText = String.format("Meat Count: %s/%s\n", collectedMeats, totalMeats);
        String currentScoreText = String.format("Current Score: %s\n", currentScore);
        g.setColor(new Color(59, 68, 75));
        g.setFont(this.font);
        g.drawString(maxScoreText, 10, 45);
        g.drawString(currentScoreText, 10, 65);

    }
}

