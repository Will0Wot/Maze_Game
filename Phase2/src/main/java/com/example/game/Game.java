package com.example.game;

import com.example.entities.*;
import com.example.gamestate.*;
import com.example.listeners.KeyBoardListener;
import com.example.listeners.MouseListener;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;


/**
 * Main class for the game that handles the game loop, state management, and rendering different game states. Serves as
 * an intermediary for input processing, delegating the operation to the current state.
 * This class is responsible for initializing the game, managing game states like play, menu, pause, etc.,
 * and handling user input through keyboard and mouse events.
 *
 * @author Pardeep Singh Manhas
 */
public class Game implements Runnable {
    private GameWindow window;
    private GameScreen screen;
    private KeyBoardListener keyInputs;
    private Thread gameThread;
    private final int UPDATES_PER_SECOND = 144;
    private int initialScreenWidth;
    private int initialScreenHeight;
    private Play playState;
    private Menu menuState;
    private Exit exitState;
    private Win winState;
    private Defeat defeatState;
    private Pause pauseState;
    private HashMap<Gamestate, State> allStates;
    private State currentState;


    /**
     * Initializes the game by setting up states, UI components, and input listeners before starting the game loop. The game
     * loop is run on a separate thread.
     *
     * @author Pardeep Singh Manhas
     */
    public Game() {
        initializeGameStates();
        initializeUI();
        initializeListeners();
        start();
    }

    /**
     * Starts the game loop in a new thread.
     *
     * @author Pardeep Singh Manhas
     */
    private void start() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    /**
     * Sets up the game's user interface components such as the game window and screen.
     *
     * @author Pardeep Singh Manhas and Fanyi Luo
     */
    private void initializeGameStates() {
        this.allStates = new HashMap<>();
        this.playState = new Play(this);
        this.menuState = new Menu(this);
        this.exitState = new Exit(this);
        this.winState = new Win(this);
        this.defeatState = new Defeat(this);
        this.pauseState = new Pause(this);
        allStates.put(Gamestate.EXIT, exitState);
        allStates.put(Gamestate.MENU, menuState);
        allStates.put(Gamestate.PLAYING, playState);
        allStates.put(Gamestate.WIN, winState);
        allStates.put(Gamestate.DEFEAT, defeatState);
        allStates.put(Gamestate.PAUSE, pauseState);
        this.currentState = this.menuState;
    }

    /**
     * Initializes  the screen and JPanel for the game.
     *
     * @author Pardeep Singh Manhas
     */
    private void initializeUI() {
        this.initialScreenWidth = playState.getBoardDimension().width;
        this.initialScreenHeight = playState.getBoardDimension().height;
        this.screen = new GameScreen(initialScreenWidth, initialScreenHeight, this);
        this.window = new GameWindow();
        this.window.add(screen);
        this.window.pack();
    }

    /**
     * Initializes the mouse and keyboard inputs for the game. Redirects them to the GameScreen.
     *
     * @author Pardeep Singh Manhas
     */
    private void initializeListeners() {
        this.keyInputs = new KeyBoardListener(this);
        this.screen.addKeyListener(keyInputs);
        this.screen.addMouseListener(new MouseListener(this));
        this.screen.requestFocus();
    }

    /**
     * Handles keyboard inputs by redirecting them to the current state for processing.
     *
     * @param e KeyEvent associated with the key press.
     * @author Pardeep Singh Manhas
     */
    public void handleKeyBoardPress(KeyEvent e) {
        this.currentState.handleKeyBoardPress(e);
    }

    /**
     * Handles keyboard releases by redirecting them to the current state for processing.
     *
     * @param e KeyEvent associated with the key release.
     * @author Pardeep Singh Manhas
     */
    public void handleKeyBoardRelease(KeyEvent e) {
        this.currentState.handleKeyBoardRelease(e);
    }

    /**
     * Handles mouse pressed events by redirecting them to the current state for processing.
     *
     * @param e MouseEvent associated with the mouse press.
     * @author Pardeep Singh Manhas and Fanyi Luo
     */
    public void handleMousePressed(MouseEvent e) {
        this.currentState.handleMousePressed(e);
    }

    /**
     * Handles mouse released events by redirecting them to the current state for processing.
     *
     * @param e MouseEvent associated with the mouse press.
     * @author Pardeep Singh Manhas and Fanyi Luo
     */

    public void handleMouseReleased(MouseEvent e) {
        this.currentState.handleMouseReleased(e);
    }
    /**
     * Retrieves the width of the game screen.
     *
     * @return The initial screen width in pixels.
     * @author Pardeep Singh Manhas
     */
    public int getScreenWidth() {
        return initialScreenWidth;
    }

    /**
     * Retrieves the height of the game screen.
     *
     * @return The initial screen height in pixels.
     * @author Pardeep Singh Manhas
     */
    public int getScreenHeight() {
        return initialScreenHeight;
    }

    /**
     * Retrieves the player character from the play state of the game. Used to interact between states.
     *
     * @return The player character entity, typically a Knight object.
     * @author Pardeep Singh Manhas
     */
    public Knight getPlayer() {
        return playState.getPlayer();
    }

    /**
     * Gets the currently active game state.
     *
     * @return The current game state as a State object. This could be any of the game states like play, menu, pause, etc.
     * @author Pardeep Singh Manhas
     */
    public State getCurrentState() {
        return currentState;
    }

    /**
     * Resets the Play State to its initial state. Typically used to restart the game or reload the level in the play state.
     *
     * @author Pardeep Singh Manhas and Fanyi Luo
     */
    public void resetMap() {
        playState.reset();
    }

    /**
     * Sets the current game state to a specified state. This allows for changing between various states like play, pause, win, defeat, etc.
     *
     * @param state The desired game state to switch to, as defined in the Gamestate enum. Examples are PLAY, PAUSE, etc.
     * @author Pardeep Singh Manhas
     */
    public void setCurrentState(Gamestate state) {
        this.currentState = allStates.get(state);
    }

    /**
     * Checks if the current game state matches the specified state.
     *
     * @param state The game state to check against the current state.
     * @return true if the current state matches the specified state, false otherwise.
     * @author Pardeep Singh Manhas
     */
    public boolean isCurrentState(Gamestate state) {
        return allStates.get(state) == currentState;
    }

    /**
     * Retrieves a specific game State. Can be used for testing the existence of a State, or for modifying a retrieved
     * State.
     *
     * @param state The desired State to retrieve, as defined in Gamestate.
     * @return The game state corresponding to the specified Gamestate enum value.
     * @author Pardeep Singh Manhas
     */
    public State getState(Gamestate state) {
        return allStates.get(state);
    }


    /**
     * Updates the current game state. This method is called at each iteration of the game loop to process game logic.
     * If the current state is the exit state, the game will terminate.
     *
     * @author Pardeep Singh Manhas and Fanyi Luo
     */
    private void update() {
        currentState.update();
        if (currentState == exitState) {
            System.exit(0);
        }
    }

    /**
     * Renders the current game state. This method is called after updating the game state to draw the game's visual elements.
     * The playing state is always rendered, and the current state is rendered on top of it unless the current state is the playing state.
     * The playing state acts a background image for other states when it is not the current state.
     *
     * @param g The Graphics object used for rendering the game's visual elements.
     * @author Pardeep Singh Manhas
     */
    public void render(java.awt.Graphics g) {
        allStates.get(Gamestate.PLAYING).render(g);
        if (currentState != allStates.get(Gamestate.PLAYING))
            currentState.render(g);
    }

    /**
     * Runs the game loop, which includes updating the current state and repainting the screen.
     * This method adheres to a constant updated rate of 144FPS.
     *
     * @author Pardeep Singh Manhas
     */
    @Override
    public void run() {
        double timePerUpdate = 1000000000.0 / UPDATES_PER_SECOND;
        long lastUpdateTime = System.nanoTime();
        long currentTime;
        double updatesDue = 0;

        while (true) {
            currentTime = System.nanoTime();
            updatesDue += (currentTime - lastUpdateTime) / timePerUpdate;
            lastUpdateTime = currentTime;
            if (updatesDue >= 1) {
                update();
                screen.repaint();
                updatesDue = updatesDue - 1;
            }
        }
    }

}