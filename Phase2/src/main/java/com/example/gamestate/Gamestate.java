package com.example.gamestate;

/**
 * Enum Name: Gamestate
 * Description: Represents the different states of the game.
 * States include MENU for when the player is navigating the game menu,
 * PLAYING for when the game is actively being played, and PAUSE for when the game is paused.
 * The game starts in the MENU state.
 *
 * @Author: Tom
 */
public enum Gamestate {
    MENU(0), PLAYING(1), PAUSE(2), DEFEAT(3), EXIT(4), WIN(5);
    private int state = 0;
    Gamestate(int state) {
        this.state = state;
    }
}
