package com.example.game;

import com.example.utils.ResourceLoader;

import java.awt.*;
import java.text.DecimalFormat;

/**
 * Represents a timer for the game, tracking the elapsed time.
 */
public class GameTimer {
    private int secondsElapsed;
    private DecimalFormat timeFormat;
    private long lastUpdateTime;

    private Font timerFont;

    /**
     * Constructs a new GameTimer with initial values.
     */
    public GameTimer() {
        this.secondsElapsed = 0;
        this.timeFormat = new DecimalFormat("00");
        initializeFont();
        resetLastUpdateTime();
    }

    /**
     * Renders the timer on the screen.
     *
     * @param g the graphics context to render on
     */
    public void render(Graphics g) {
        String timeText = formatTimeText();
        drawTimerText(g, timeText);
    }

    /**
     * Updates the timer by incrementing the elapsed seconds.
     */
    public void update() {
        long currentTime = System.currentTimeMillis();
        if (hasSecondElapsed(currentTime)) {
            incrementSecondsElapsed();
            updateLastUpdateTime(currentTime);
        }
    }

    /**
     * Resets the timer to zero.
     */
    public void reset() {
        secondsElapsed = 0;
    }

    /**
     * Gets the total elapsed seconds.
     *
     * @return the total elapsed seconds
     */
    public int getSecondsElapsed() {
        return secondsElapsed;
    }

    private void initializeFont() {
        Font temp = ResourceLoader.getFont(ResourceLoader.MEDIEVAL_FONT);
        timerFont = temp.deriveFont(18f);
    }

    private String formatTimeText() {
        int hours = secondsElapsed / 3600;
        int minutes = (secondsElapsed % 3600) / 60;
        int seconds = secondsElapsed % 60;
        return "Time: " + timeFormat.format(hours) + ":" +
                timeFormat.format(minutes) + ":" + timeFormat.format(seconds);
    }

    private void drawTimerText(Graphics g, String timeText) {
        g.setColor(new Color(59, 68, 75));
        g.setFont(timerFont);
        g.drawString(timeText, 10, 25);
    }

    private boolean hasSecondElapsed(long currentTime) {
        return currentTime - lastUpdateTime >= 1000;
    }

    private void incrementSecondsElapsed() {
        secondsElapsed++;
    }

    private void updateLastUpdateTime(long currentTime) {
        lastUpdateTime = currentTime;
    }

    private void resetLastUpdateTime() {
        lastUpdateTime = System.currentTimeMillis();
    }
}
