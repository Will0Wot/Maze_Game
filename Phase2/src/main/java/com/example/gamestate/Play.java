package com.example.gamestate;

import com.example.entities.*;
import com.example.game.*;
import com.example.levels.Board;
import com.example.utils.Directions;
import com.example.utils.ResourceLoader;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;


/**
 * Represents the playing state of the game where the main gameplay occurs. This state handles
 * game entities such as the player character (Knight), enemies (Goblins), and collectibles (Meats, Coins).
 * It is responsible for updating and rendering these entities as well as handling player input in addition to rendering
 * the map. The game transitions out of this state to other states such as win, defeat, or pause based on in-game events.
 * This instance also handles camera movements, allowing for only a portion of the current game world to be displayed,
 * and manages the rendering of any helpful information to the top left of the screen.
 *
 * @author Pardeep Singh Manhas
 */
public class Play extends State {
    private Knight knight;
    private final ConcurrentLinkedDeque<Tree> trees = new ConcurrentLinkedDeque<>();
    private final ConcurrentLinkedDeque<Goblin> goblins = new ConcurrentLinkedDeque<>();
    private final ConcurrentLinkedDeque<Dynamite> dynamites = new ConcurrentLinkedDeque<>();
    private final ConcurrentLinkedDeque<House> houses = new ConcurrentLinkedDeque<>();
    private final ConcurrentLinkedDeque<Coin> coins = new ConcurrentLinkedDeque<>();
    private final ConcurrentLinkedDeque<Meat> meats = new ConcurrentLinkedDeque<>();
    private int victoryMeatCount;
    private Board board;
    private final Dimension boardSize;
    private GameTimer timer = new GameTimer();
    private final Scoreboard scoreboard;
    private final HelpInformation help = new HelpInformation();
    private final HelpBanner banner = new HelpBanner();
    private GameCamera testCamera;

    /**
     * Initializes the playing state with the game board, entities, and UI components. The Entities to create are created
     * by the Board class, and are copied by this instance.
     *
     * @param game The main game controller this state is part of.
     * @author Pardeep Singh Manhas, William Desa, and Manya Sharma
     */
    public Play(Game game) {
        super(game);
        initializeBoard();
        initializeEntities();
        timer = new GameTimer();
        this.boardSize = new Dimension(board.getCellSize() * 10, board.getCellSize() * 7);
        this.victoryMeatCount = meats.size();
        scoreboard = new Scoreboard(victoryMeatCount);
        initializeCamera();
    }

    /**
     * Initializes the game board by loading a level from a specified path by utilizing ResourceLoader.
     *
     * @author Pardeep Singh Manhas
     */
    private void initializeBoard() {
        this.board = new Board(ResourceLoader.LEVEL_ONE_PATH, this);
    }

    /**
     * Initializes all game entities, including the player, enemies, and collectibles, by
     * retrieving them from the game board.
     *
     * @author Pardeep Singh Manhas, William Desa, and Manya Sharma
     */
    private void initializeEntities() {
        this.knight = board.getKnights().get(0);
        knight.setPlay(this);
        goblins.addAll(board.getGoblins());
        dynamites.addAll(board.getDynamites());
        houses.addAll(board.getHouses());
        meats.addAll(board.getMeats());
        coins.addAll(board.getCoins());
        trees.addAll(board.getTrees());
    }

    /**
     * Initializes the camera with settings based on the player's position and the dimensions of the game board. Will
     * only display a certain portion of the game world.
     *
     * @author Pardeep Singh Manhas
     */
    private void initializeCamera() {
        float collisionX = knight.getCollisionBox().x;
        float collisionY = knight.getCollisionBox().y;
        int maxX = board.getBoardWidthPixels();
        int maxY = board.getBoardHeightPixels();
        this.testCamera = new GameCamera(collisionX, collisionY, boardSize.width, boardSize.height, maxX, maxY);
    }

    /**
     * Checks if the player can move to a specified position without colliding with non-passable board cells. Non-passable
     * board cells are cells not included in the dimensions of the board or those covered by a tree. Calculates if any
     * corner of the player's collision box will enter a non-passable board cell.
     *
     * @param potentialXPos The future X position of the player.
     * @param potentialYPos The future Y position of the player.
     * @param playerWidth   The width of the player's collision box.
     * @param playerHeight  The height of the player's collision box.
     * @return True if the player can move to the position, false otherwise.
     * @author Pardeep Singh Manhas
     */
    public boolean canPlayerMove(float potentialXPos, float potentialYPos, int playerWidth, int playerHeight) {
        boolean topLeftMovable = isBoardCellPassable(potentialXPos, potentialYPos);
        boolean topRightMovable = isBoardCellPassable(potentialXPos + playerWidth, potentialYPos);
        boolean bottomLeftMovable = isBoardCellPassable(potentialXPos, potentialYPos + playerHeight);
        boolean bottomRightMovable = isBoardCellPassable(potentialXPos + playerWidth, potentialYPos + playerHeight);
        return topLeftMovable && topRightMovable && bottomLeftMovable && bottomRightMovable;
    }

    /**
     * A helper method for canPlayerMove. Determines if a specific cell on the game board is passable based on its X and Y coordinates.
     * Calculates if the player's collision box will enter a cell with a tree or a cell outside the map. Utilizes the Cell
     * class' isCellPassable method.
     *
     * @param x The X coordinate of one of the player's collision box corners.
     * @param y The Y coordinate of one of the player's collision box corners.
     * @return True if the cell is passable, false otherwise.
     * @author Pardeep Singh Manhas
     */
    private boolean isBoardCellPassable(float x, float y) {
        float size = board.getCellSize();
        x = x / size;
        y = y / size;
        return board.isCellPassable((int) y, (int) x);
    }


    /**
     * Retrieves the player character.
     *
     * @return The Knight controlled by the player character.
     * @author Pardeep Singh Manhas
     */
    public Knight getPlayer() {
        return this.knight;
    }

    public Dimension getBoardDimension() {
        return boardSize;
    }

    /**
     * Resets the game state, including entities and the game board, typically for starting a new game. Regenerates the
     * board and recopies entities. Likewise, resets the timer and scoreboard for the game.
     *
     * @author Pardeep Singh Manhas and Fanyi Luo
     */
    public void reset() {
        resetEntities();
        initializeBoard();
        initializeEntities();
        knight.reset();
        getGame().setCurrentState(Gamestate.PLAYING);
        this.victoryMeatCount = meats.size();
        timer.reset();
        scoreboard.resetScores(victoryMeatCount);
    }

    /**
     * Helper method for reset. Clears all the lists of entities to prevent duplicates from existing within the list.
     *
     * @author Pardeep Singh Manhas
     */
    private void resetEntities() {
        houses.clear();
        meats.clear();
        coins.clear();
        trees.clear();
        goblins.clear();
        dynamites.clear();
    }


    /**
     * Renders all game elements including the game board, entities, and UI components every game update. Typically, moving entities such
     * as Goblins and Knights will be rendered over stationary ones. Also renders the timer and scoreboard.
     *
     * @param g The Graphics object used for drawing.
     * @author Pardeep Singh Manhas, William Desa, and Manya Sharma
     */
    @Override
    public void render(Graphics g) {
        board.render(g, (int) testCamera.getCameraXPos(), (int) testCamera.getCameraYPos());
        for (Tree tree : trees) {
            tree.render(g, (int) testCamera.getCameraXPos(), (int) testCamera.getCameraYPos());
        }
        for (House house : houses) {
            house.render(g, (int) testCamera.getCameraXPos(), (int) testCamera.getCameraYPos());
        }
        for (Meat meat : meats) {
            meat.render(g, (int) testCamera.getCameraXPos(), (int) testCamera.getCameraYPos());
        }
        for (Coin coin : coins) {
            coin.render(g, (int) testCamera.getCameraXPos(), (int) testCamera.getCameraYPos());
        }
        for (Dynamite dynamite : dynamites) {
            dynamite.render(g, (int) testCamera.getCameraXPos(), (int) testCamera.getCameraYPos());
        }
        for (Goblin goblin : goblins) {
            goblin.render(g, (int) testCamera.getCameraXPos(), (int) testCamera.getCameraYPos());
        }
        knight.render(g, (int) testCamera.getCameraXPos(), (int) testCamera.getCameraYPos());
        banner.render(g, (int) testCamera.getCameraXPos(), (int) testCamera.getCameraYPos());
        timer.render(g);
        scoreboard.render(g, (int) testCamera.getCameraXPos(), (int) testCamera.getCameraYPos());
        help.render(g, (int) testCamera.getCameraXPos(), (int) testCamera.getCameraYPos());
    }


    /**
     * Updates the game state, including all game entities and the game timer. Game entities detect whether they are
     * supposed to be colliding or interacting with each other during this update. Also updates the Camera of the game,
     * offsetting all other images relative to it. The game logic of the game is handled in this method as well, determining
     * if the knight can touch the house and win the game or if the knight's score is negative, and he has died.
     *
     * @author Pardeep Singh Manhas, William Desa, and Manya Sharma
     */
    @Override
    public void update() {
        knight.update();
        testCamera.incrementCameraX(knight.getCollisionBox().x);
        testCamera.incrementCameraY(knight.getCollisionBox().y);

        for (Goblin goblin : goblins) {
            goblin.update();
            Rectangle2D.Float playerCollisionBox = knight.getCollisionBox();
            if (playerCollisionBox.intersects(goblin.getEnragedRange())) {
                goblin.setEnraged(true);
                if (playerCollisionBox.intersects(goblin.getCollisionBox())) {
                    goblin.interact(knight);
                    getGame().setCurrentState(Gamestate.DEFEAT);

                }
            } else {
                goblin.setEnraged(false);
            }

        }

        for (Dynamite dynamite : dynamites) {
            dynamite.update();
            Rectangle2D.Float playerCollisionBox = knight.getCollisionBox();
            if (playerCollisionBox.intersects(dynamite.getCollisionBox())) {
                dynamite.interact(knight);
                scoreboard.updateCurrentScore(dynamite.getInteractableAmount());
                if (knight.isDead()) {
                    getGame().setCurrentState(Gamestate.DEFEAT);
                }
                dynamites.remove(dynamite);
            }
        }

        for (House house : houses) {
            Rectangle2D.Float playerCollisionBox = knight.getCollisionBox();
            if (playerCollisionBox.intersects(house.getCollisionBox())) {
                if (knight.getMandatoryRewardCount() == victoryMeatCount) {
                    getGame().setCurrentState(Gamestate.WIN);
                }

            }
        }

        for (Meat meat : meats) {
            Rectangle2D.Float playerCollisionBox = knight.getCollisionBox();
            if (playerCollisionBox.intersects(meat.getCollisionBox())) {
                meat.interact(knight);
                knight.incrementMandatoryRewardCount();
                scoreboard.updateCurrentScore(meat.getInteractableAmount());
                scoreboard.incrementMeatCount();
                meats.remove(meat);
            }
        }

        for (Coin coin : coins) {
            Rectangle2D.Float playerCollisionBox = knight.getCollisionBox();
            if (playerCollisionBox.intersects(coin.getCollisionBox())) {
                coin.interact(knight);
                scoreboard.updateCurrentScore(coin.getInteractableAmount());
                coins.remove(coin);
            }
        }
        timer.update();
    }


    /**
     * Handles key press events to control the player character. Does this by forwarding
     * the key press to the knight, and manipulating its active directions.
     *
     * @param e The KeyEvent associated with the key press.
     * @author Pardeep Singh Manhas
     */
    @Override
    public void handleKeyBoardPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                knight.setActiveDirection(Directions.UP, true);
                break;
            case KeyEvent.VK_A:
                knight.setActiveDirection(Directions.LEFT, true);
                break;
            case KeyEvent.VK_S:
                knight.setActiveDirection(Directions.DOWN, true);
                break;
            case KeyEvent.VK_D:
                knight.setActiveDirection(Directions.RIGHT, true);
                break;
        }
    }


    /**
     * Handles key release events to update the player character's state and directions.
     *
     * @param e The KeyEvent associated with the key release.
     * @author Pardeep Singh Manhas
     */
    public void handleKeyBoardRelease(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                knight.setActiveDirection(Directions.UP, false);
                break;
            case KeyEvent.VK_A:
                knight.setActiveDirection(Directions.LEFT, false);
                break;
            case KeyEvent.VK_S:
                knight.setActiveDirection(Directions.DOWN, false);
                break;
            case KeyEvent.VK_D:
                knight.setActiveDirection(Directions.RIGHT, false);
                break;
        }
    }

    /**
     * A method that handles mouse inputs. Not currently utilized.
     *
     * @param e A MouseEvent that can be translated to some action within the current state context.
     * @author Pardeep Singh Manhas
     */
    @Override
    public void handleMouseInput(MouseEvent e) {
    }

    /**
     * A method that handles mouse presses. Not currently utilized.
     *
     * @param e A MouseEvent that can be translated to some action within the current state context.
     * @author Fanyi Luo
     */
    @Override
    public void handleMousePressed(MouseEvent e) {

    }

    /**
     * A method that handles mouse releases. Not currently utilized.
     *
     * @param e A MouseEvent that can be translated to some action within the current state context.
     * @author Fanyi Luo
     */
    @Override
    public void handleMouseReleased(MouseEvent e) {

    }
}