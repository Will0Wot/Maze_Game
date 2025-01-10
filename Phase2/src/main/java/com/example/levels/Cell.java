package com.example.levels;

import com.example.entities.GameEntity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


/**
 * Represents a single cell in the game's level grid. Each cell can contain multiple game entities
 * and has a background image to represent its appearance, which is typically a grass image. The cell also has a passable attribute
 * indicating whether it can be traversed by characters or entities within the game.
 * This class provides methods to manage the entities within a cell and to manipulate its passable attribute. It also
 * additionally allows for the changing of its background image.
 *
 * @author Pardeep Singh Manhas
 */
public class Cell {
    private ArrayList<GameEntity> entitiesPresentOnCell;
    private BufferedImage cellBackgroundImage;
    private boolean passable;

    /**
     * Constructs a {@code Cell} with a specified background image and passability.
     *
     * @param cellBackgroundImage The background image for the cell.
     * @param passable            Indicates whether the cell is passable.
     * @author Pardeep Singh Manhas
     */
    public Cell(BufferedImage cellBackgroundImage, boolean passable) {
        this.entitiesPresentOnCell = new ArrayList<>();
        this.cellBackgroundImage = cellBackgroundImage;
        this.passable = passable;
    }

    /**
     * Returns the list of entities within this cell.
     *
     * @return A list of GameEntity objects present on this cell.
     * @author Pardeep Singh Manhas
     */
    public ArrayList<GameEntity> getEntitiesPresentOnCell() {
        return entitiesPresentOnCell;
    }

    /**
     * Adds a game entity to this cell if it's not already present.
     *
     * @param entity The GameEntity object to be added.
     */
    public void addGameEntity(GameEntity entity) {
        if (!entitiesPresentOnCell.contains(entity)) {
            entitiesPresentOnCell.add(entity);
        }
    }
    /**
     * Removes a game entity from this cell.
     *
     * @param entity The GameEntity object to be removed.
     * @return True if the entity was removed/was on the cell, false otherwise.
     */
    public boolean removeGameEntity(GameEntity entity) {
        return entitiesPresentOnCell.remove(entity);
    }

    /**
     * Checks if the cell is passable. That is, that no blocking entities are found on it.
     *
     * @return True if the cell is passable, false otherwise.
     */
    public boolean isPassable() {
        return passable;
    }

    /**
     * Sets the passability of the cell.
     *
     * @param passable true to make the cell passable, false otherwise.
     */
    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    /**
     * Gets the background image of the cell.
     *
     * @return The background BufferedImage of the cell.
     */
    public BufferedImage getCellBackgroundImage() {
        return cellBackgroundImage;
    }
}