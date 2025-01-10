package com.example.levels;

import com.example.entities.Dynamite;
import com.example.entities.GameEntity;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.example.utils.Position;
import com.example.entities.GameImage;
import com.example.utils.EntityStates;

import java.awt.image.BufferedImage;

public class CellTest {

    private BufferedImage backgroundImage;
    private Dynamite dynamite;
    private Cell passableCell;
    private Cell blockedCell;

    @BeforeEach
    void setup() {
        backgroundImage = mock(BufferedImage.class);
        dynamite = mock(Dynamite.class);
        passableCell = new Cell(backgroundImage, true);
        blockedCell = new Cell(backgroundImage, false);
    }

    @Test
    public void getEntitiesPresentOnCell() {
        ArrayList<GameEntity> expected = new ArrayList<>();
        ArrayList<GameEntity> actual = passableCell.getEntitiesPresentOnCell();
        assertArrayEquals(expected.toArray(), actual.toArray());
        passableCell.addGameEntity(dynamite);
        ArrayList<GameEntity> actualFalse = passableCell.getEntitiesPresentOnCell();
        assertNotEquals(expected.size(), actualFalse.size());
    }

    @Test
    public void addGameEntity() {
        ArrayList<GameEntity> actual = new ArrayList<>();
        actual.add(dynamite);
        passableCell.addGameEntity(dynamite);
        assertArrayEquals(actual.toArray(), passableCell.getEntitiesPresentOnCell().toArray());
    }

    //
    @Test
    public void removeGameEntity() {
        boolean expected = false;
        boolean actual = passableCell.removeGameEntity(dynamite);
        assertEquals(expected, actual);
        passableCell.addGameEntity(dynamite);
        expected = true;
        assertEquals(expected, passableCell.removeGameEntity(dynamite));
    }

    @Test
    public void isPassable() {
        boolean expected = true;
        boolean actual = passableCell.isPassable();
        assertEquals(expected, actual);
        assertNotEquals(expected, blockedCell.isPassable());
    }

    @Test
    public void setPassable() {
        blockedCell.setPassable(true);
        boolean passable = true;
        assertEquals(passable, blockedCell.isPassable());
    }
}
