package com.example.gamestate;

import com.example.game.Game;
//import com.example.ui.MenuButton;
import com.example.ui.PauseButton;
import com.example.utils.ResourceLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;

/**
 * The {@code Pause} class represents the pause state of the game.
 * It handles the display and functionality of the pause menu, allowing players to continue, restart, or exit the game.
 * This state is part of the game's state system and manages pause-related interactions.
 *
 * @author Tom
 */
public class Pause extends State {

    private PauseButton[] b = new PauseButton[3];
    private BufferedImage bgImg;
    private BufferedImage topImg;
    private int textX = (1200+18)/2;
    private int textY = (800-36)/2;
    private int bgX = (1272-192*2)/2;
    private int bgY = (860-192*2)/2;
    private Font font;

    /**
     * Constructs a Pause state for the given game.
     * Initializes buttons and background images.
     *
     * @param game The game this state is part of.
     */
    public Pause(Game game) {
        super(game);
        createButtons();
        createBackground();
        Font temp = ResourceLoader.getFont(ResourceLoader.MEDIEVAL_FONT);
        font = temp.deriveFont(18f);
        font = font.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_MEDIUM));
    }

    /**
     * Draws the text for the win state on the given graphics context.
     *
     * @param g The graphics context to draw on.
     */
    private void drawText(Graphics g) {

        g.setColor(new Color(59,68,75));
        g.setFont(this.font);
        // Draw the text
        String textPlay = "CONTINUE";
        String textExit = "EXIT";
        String textTitle = "MENU";
        String textReplay = "RESTART";
        g.drawString(textPlay, textX-30, textY);
        g.drawString(textExit, textX, textY+100);
        g.drawString(textTitle, textX, textY-65);
        g.drawString(textReplay, textX-20, textY+50);


    }
    /**
     * Loads the background images for the win state.
     */
    private void createBackground() {
        try {
            bgImg = ResourceLoader.getImage(ResourceLoader.BUTTON_VERTICAL_BG);
            topImg = ResourceLoader.getImage(ResourceLoader.MENU_TOP_BLUE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Initializes the buttons used in the state.
     */
    private void createButtons(){
        b[0] = new PauseButton(120,100,0,Gamestate.PLAYING);
        b[1] = new PauseButton(120,-100,1,Gamestate.EXIT);
        b[2] = new PauseButton(120,0,1,Gamestate.PLAYING);
//        //test win and lose
//        b[1] = new MenuButton(120,-100,1,Gamestate.WIN);
//        b[1] = new MenuButton(120,-100,1,Gamestate.DEFEAT);
    }

    /**
     * Checks if the mouse event occurred inside the bounds of a WinButton.
     *
     * @param e The mouse event.
     * @param mb The WinButton to check.
     * @return true if the mouse event is inside the button; false otherwise.
     */
    public boolean isInside(MouseEvent e, PauseButton mb){
        return mb.getBounds().contains(e.getX(),e.getY());
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(bgImg, bgX,bgY,bgImg.getWidth()*2,bgImg.getHeight()*2,null);
        for (PauseButton mb : b){
            mb.draw(g);
        }
        g.drawImage(topImg, bgX,bgY+50,topImg.getWidth()*2,topImg.getHeight(),null);
        //draw the text
        drawText(g);
    }

    @Override
    public void update() {
        for (PauseButton mb : b){
            mb.update();
        }
    }

    @Override
    public void handleKeyBoardPress(KeyEvent e) {

    }

    @Override
    public void handleKeyBoardRelease(KeyEvent e) {

    }

    @Override
    public void handleMouseInput(MouseEvent e) {
        //System.out.println("Clicked");
    }


    @Override
    public void handleMousePressed(MouseEvent e) {
        System.out.println("is pressed");
        for (PauseButton mb : b){
            if (isInside(e,mb)){
                System.out.println("set pressover to true");
                mb.setPressOver(true);
                break;
            }

        }
    }

    @Override
    public void handleMouseReleased(MouseEvent e) {
        for (PauseButton mb : b){
            if (isInside(e,mb)){
                System.out.println("is inside");
                if(mb.isPressOver()) {
                    System.out.println("is released");
                    // The new way to change states is setCurrentState(Gamestate state) in game class. This method only toggles between
                    // playing and menu now. An example is in keyboardlistener class that utilizes it to recreate toggleMenuState.
                    // - Sunny/Pardeep
                    // fixed - Tom
//                    if (getGame().isCurrentState(Gamestate.MENU)){
//                        getGame().setCurrentState(mb.getState());
//                    } else {
//                        getGame().setCurrentState(Gamestate.MENU);
//                    }
                    getGame().setCurrentState(mb.getState());
                    if (b[2].isPressOver()){
                        getGame().resetMap();
                    }
                    System.out.println(mb.getState());

                }
                break;
            }
        }
        resetButtons();
        System.out.println("reset finish");
    }

//    @Override
//    public void handleMouseMoved(MouseEvent e) {
//        System.out.println("is moved");
//        for (MenuButton mb : b)
//            mb.setNotPressOver(false);
//        for (MenuButton mb : b){
//            if (isInside(e,mb)){
//                mb.setNotPressOver(true);
//                break;
//            }
//        }
//    }

    /**
     * Resets the states of all WinButtons to their initial states.
     */
    private void resetButtons() {
        for (PauseButton mb : b)
            mb.resetBools();
    }


}
