package com.example.game;

import com.example.utils.ResourceLoader;

import javax.swing.*;
import java.awt.*;

/**
 * A JPanel subclass that displays help information and controls for the game.
 * It utilizes a custom font for rendering the text, loaded through the ResourceLoader class.
 * This panel can be added to a game window or overlay to provide players with necessary instructions.
 *
 * @author Pardeep Singh Manhas
 */
public class HelpInformation extends JPanel {

    private final Font font;

    /**
     * Constructs a HelpInformation instance by loading and setting up the font used for displaying the helpful text.
     *
     * @author Pardeep Singh Manhas
     */
    public HelpInformation() {
        Font temp = ResourceLoader.getFont(ResourceLoader.MEDIEVAL_FONT);
        font = temp.deriveFont(18f);
    }

    /**
     * Renders the help information text onto the specified graphics context.
     * This method positions and draws key binding instructions and game tips using the preloaded font.
     *
     * @param g        The Graphics object used for drawing the text. Typically the same one used to paint the game screen.
     * @param x_offset The horizontal offset from the left where the text should start being drawn.
     * @param y_offset The vertical offset from the top where the text should start being drawn.
     * @author Pardeep Singh Manhas
     */
    public void render(Graphics g, int x_offset, int y_offset) {
        g.setColor(new Color(59, 68, 75));
        g.setFont(this.font);
        String upMessage = "W = Up";
        g.drawString(upMessage, 10, 85);
        String leftMessage = "A = Left";
        g.drawString(leftMessage, 10, 105);
        String downMessage = "S = Down";
        g.drawString(downMessage, 10, 125);
        String rightMessage = "D = Right";
        g.drawString(rightMessage, 10, 145);
        String pauseMessage = "ESC = Pause";
        g.drawString(pauseMessage, 10, 165);
        g.drawString("Collect all the meat", 10, 185);
        g.drawString("and return home.", 10, 205);
    }
}