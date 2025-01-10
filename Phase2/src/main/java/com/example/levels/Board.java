package com.example.levels;

import com.example.ai.Pathfinder;
import com.example.entities.*;
import com.example.utils.EntityStates;
import com.example.gamestate.Play;
import com.example.utils.Position;
import com.example.utils.ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a game board for rendering and managing a grid of cells, each with a background image. The cells of the
 * board can contain entities. Additionally, is how the levels of project are created and reset. Keeps lists of the
 * entities that it creates as it initializes the level.
 *
 * @author Pardeep Singh Manhas
 */

public class Board {
    public static int DEFAULT_SIZE = 125;
    private com.example.levels.Cell[][] board;
    private final int[][] currentLevel;
    private BufferedImage backGroundImage = null;
    private final int cellRows;
    private final int cellColumns;
    private final int cellSize;
    private final ArrayList<Tree> trees = new ArrayList<>();
    private final ArrayList<House> houses = new ArrayList<>();
    private final ArrayList<Meat> meats = new ArrayList<>();
    private final ArrayList<Coin> coins = new ArrayList<>();
    private final ArrayList<Dynamite> dynamites = new ArrayList<>();

    private final ArrayList<Goblin> goblins = new ArrayList<>();
    private final ArrayList<Knight> knights = new ArrayList<>();

    /**
     * Constructs a representation of the board based on a filepath and the current Play instance. This instance will read
     * the filepath (which must lead to a rectangular matrix of integers) and will construct a board to represent it. The
     * Play instance will be used by this instance to instantiate Entities related to those that it finds as it creates the board.
     *
     * @param filepath The path to the level file containing the layout of the board. Must be a rectangular matrix of integers.
     * @param play     The Game's current Play instance.
     * @author Pardeep Singh Manhas
     */
    public Board(String filepath, Play play) {
        this.currentLevel = ResourceLoader.getLevel(filepath);
        this.cellRows = currentLevel.length;
        this.cellColumns = currentLevel[0].length;
        this.cellSize = DEFAULT_SIZE;
        try {
            this.backGroundImage = ResourceLoader.getImage(ResourceLoader.GRASS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        initializeBoard(play);
    }

    /**
     * Constructs a representation of the board based on an integer array and the current Play instance. This instance will utilize
     * the integer matrix and will construct a board to represent it. The
     * Play instance will be used by this instance to instantiate Entities related to those that it finds as it creates the board.
     *
     * @param currentLevel A matrix of integers representing the current level.
     * @param play     The Game's current Play instance.
     * @author Pardeep Singh Manhas
     */
    public Board(int [][] currentLevel, Play play) {
        this.currentLevel = currentLevel;
        this.cellRows = currentLevel.length;
        this.cellColumns = currentLevel[0].length;
        this.cellSize = DEFAULT_SIZE;
        try {
            this.backGroundImage = ResourceLoader.getImage(ResourceLoader.GRASS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        initializeBoard(play);
    }

    /**
     * Initializes the Cell objects of this Board instance by sequentially examining its currentLevel field. Instantiates
     * Entities as it scans the currentLevel matrix.
     *
     * @author Pardeep Singh Manhas
     */

    private void initializeBoard(Play play) {
        board = new Cell[this.cellRows][this.cellColumns];
        // Magic numbers are Grass related.
        BufferedImage subBack = backGroundImage.getSubimage(15, 10, cellSize, cellSize);
        for (int i = 0; i < this.cellRows; i++) {
            for (int j = 0; j < this.cellColumns; j++) {
                board[i][j] = new Cell(subBack, true);
                Position pos = new Position(cellSize * j, cellSize * i);
                GameImage image;
                BufferedImage img;
                Dimension dim;
                board[i][j].setPassable(true);
                switch (currentLevel[i][j]) {
                    case 1:
                        dim = new Dimension(cellSize, cellSize);
                        img = loadCellImage(ResourceLoader.TREES2_PATH);
                        image = new GameImage(img, cellSize, cellSize);
                        Tree tree = new Tree(pos, dim, image, EntityStates.IDLE);
                        board[i][j].addGameEntity(tree);
                        board[i][j].setPassable(false);
                        trees.add(tree);
                        break;
                    case 2:
                        dim = new Dimension(Knight.KNIGHT_SIZE, Knight.KNIGHT_SIZE);
                        img = loadCellImage(ResourceLoader.KNIGHT_PATH);
                        image = new GameImage(img, Knight.KNIGHT_SIZE, Knight.KNIGHT_SIZE);
                        Knight player = new Knight(pos, dim, EntityStates.IDLE, image, play);
                        board[i][j].addGameEntity(player);
                        knights.add(player);
                        break;

                    case 3:
                        dim = new Dimension(cellSize, cellSize);
                        img = loadCellImage(ResourceLoader.END_HOUSE_PATH);
                        image = new GameImage(img, House.HOUSE_WIDTH, House.HOUSE_HEIGHT);
                        House house = new House(pos, dim, image, EntityStates.IDLE);
                        board[i][j].addGameEntity(house);
                        houses.add(house);
                        break;
                    case 4:
                        dim = new Dimension(Meat.MEAT_SIZE, Meat.MEAT_SIZE);
                        img = loadCellImage(ResourceLoader.MEAT_PATH);
                        image = new GameImage(img, Meat.MEAT_SIZE, Meat.MEAT_SIZE);
                        Meat meat = new Meat(pos, dim, image, EntityStates.IDLE);
                        board[i][j].addGameEntity(meat);
                        meats.add(meat);
                        break;
                    case 5:
                        dim = new Dimension(Coin.COIN_SIZE, Coin.COIN_SIZE);
                        img = loadCellImage(ResourceLoader.GOLD_PATH);
                        image = new GameImage(img, Coin.COIN_SIZE, Coin.COIN_SIZE);
                        Coin coin = new Coin(pos, dim, image, EntityStates.IDLE);
                        board[i][j].addGameEntity(coin);
                        coins.add(coin);
                        break;
                    case 6:
                        dim = new Dimension(Dynamite.DYNAMITE_SIZE, Dynamite.DYNAMITE_SIZE);
                        img = loadCellImage(ResourceLoader.TNT_PATH);
                        image = new GameImage(img, Dynamite.DYNAMITE_SIZE, Dynamite.DYNAMITE_SIZE);
                        Dynamite dynamite = new Dynamite(pos, dim, EntityStates.IDLE, image);
                        board[i][j].addGameEntity(dynamite);
                        dynamites.add(dynamite);
                        break;
                    case 7:
                        dim = new Dimension(Goblin.GOBLIN_SIZE, Goblin.GOBLIN_SIZE);
                        img = loadCellImage(ResourceLoader.GOBLIN_PATH);
                        image = new GameImage(img, Goblin.GOBLIN_SIZE, Goblin.GOBLIN_SIZE);
                        Pathfinder pathfinder = new Pathfinder(currentLevel);
                        Goblin goblin = new Goblin(pos, dim, EntityStates.IDLE, image, knights.get(0), pathfinder);
                        board[i][j].addGameEntity(goblin);
                        goblins.add(goblin);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private BufferedImage loadCellImage(String filePath) {
        BufferedImage image;
        try {
            image = ResourceLoader.getImage(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }


    /**
     * Retrieves a list of all House entities present on the board at the time it was instantiated.
     *
     * @return A list of all House entities.
     * @author Pardeep Singh Manhas
     */
    public ArrayList<House> getHouses() {
        return houses;
    }

    /**
     * Retrieves a list of all Meat entities present on the board at the time it was instantiated.
     *
     * @return A list of Meat entities.
     * @author Pardeep Singh Manhas
     */
    public ArrayList<Meat> getMeats() {
        return meats;
    }

    /**
     * Retrieves a list of all Coin entities present on the board at the time it was instantiated.
     *
     * @return A list of Coin entities.
     * @author Pardeep Singh Manhas
     */
    public ArrayList<Coin> getCoins() {
        return coins;
    }

    /**
     * Retrieves the list of Dynamite entities present on the board at the time it was instantiated.
     *
     * @return A list of Dynamite entities.
     * @author Pardeep Singh Manhas
     */
    public ArrayList<Dynamite> getDynamites() {
        return dynamites;
    }

    /**
     * Retrieves the list of Goblin entities present on the board at the time it was instantiated.
     *
     * @return A list of Goblin entities.
     * @author Pardeep Singh Manhas
     */
    public ArrayList<Goblin> getGoblins() {
        return goblins;
    }

    /**
     * Retrieves the list of Knight entities present on the board at the time it was instantiated.
     *
     * @return A list of Knight entities.
     * @author Pardeep Singh Manhas
     */
    public ArrayList<Knight> getKnights() {
        return knights;
    }

    /**
     * Retrieves the integer matrix that used to instantiate this board class. This matrix was derived from the
     * file supplied to this instance. Generally used for pathfinding.
     *
     * @return A matrix of integers that was used to instantiate the board. Can be used for object collision and pathfinding.
     * @author Pardeep Singh Manhas
     */
    public int[][] getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Renders the board on the given graphics context. In the context of this Game, requires the graphics object of the
     * GameScreen class.
     *
     * @param g The graphics object used to paint the board.
     * @author Pardeep Singh Manhas
     */

    public void render(Graphics g, float xOff, float yOff) {
        for (int i = 0; i < cellRows; i++) {
            for (int j = 0; j < cellColumns; j++) {
                g.drawImage(board[i][j].getCellBackgroundImage(), cellSize * j - (int) xOff, cellSize * i - (int) yOff, cellSize, cellSize, null);
            }
        }
    }

    /**
     * Returns a Cell object within the Board's boundaries.
     *
     * @param row    A row of the board.
     * @param column A column of the board.
     * @return A Cell object if valid rows and columns were provided, or else null.
     * @author Pardeep Singh Manhas
     */
    private Cell getCell(int row, int column) {
        if (row < 0 || row >= cellRows || column < 0 || column >= cellColumns) {
            return null;
        }
        return board[row][column];
    }

    public ArrayList<Tree> getTrees() {
        return this.trees;
    }

    /**
     * Essentially determines if an impassable Entity instance was placed into the Cell at the time of its creation. Used to detect terrain
     * and impassable objects, such as Tree and House objects.
     *
     * @param row    A valid row in the board.
     * @param column A valid column in the board;
     * @return True if the cell has no blockable entities place upon it. False otherwise.
     * @author Pardeep Singh Manhas
     */
    public boolean isCellPassable(int row, int column) {
        return getCell(row, column).isPassable();
    }

    /**
     * Returns the width of this instance. The width is the number of cells provided during level creation (length of a
     * row).
     *
     * @return The width of the board.
     * @author Pardeep Singh Manhas
     */
    public int getBoardWidth() {
        return cellColumns;
    }

    /**
     * Returns the Height of this instance. The Height is the number of cells provided during level creation (length of a
     * Column).
     *
     * @return The height of the board.
     * @author Pardeep Singh Manhas
     */

    public int getBoardHeight() {
        return cellRows;
    }

    /**
     * Returns the width of the board when accounting for the screen size. It is the actual size of a cell on the
     * game's screen multiplied by the width of this instance.
     *
     * @return The width of the board when accounting for the size of a cell.
     * @author Pardeep Singh Manhas
     */
    public int getBoardWidthPixels() {
        return cellSize * cellColumns;
    }

    /**
     * Returns the height of the board when accounting for the screen size. It is the actual size of a cell on the
     * game's screen multiplied by the height of this instance.
     *
     * @return The height of the board when accounting for the size of a cell.
     * @author Pardeep Singh Manhas
     */

    public int getBoardHeightPixels() {
        return cellSize * cellRows;
    }

    /**
     * Returns the actual size of the cell in terms of pixels. Each Cell is 125 pixels by default.
     *
     * @return The number of pixels in a cell of the board.
     * @author Pardeep Singh Manhas
     */

    public int getCellSize() {
        return this.cellSize;
    }
}
