package com.example.levels;

import com.example.game.Game;
import com.example.gamestate.Play;
import com.example.utils.ResourceLoader;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import com.example.entities.House;
import com.example.entities.Meat;
import com.example.entities.Coin;
import com.example.entities.Dynamite;
import com.example.entities.Goblin;
import com.example.entities.Knight;
import com.example.entities.Tree;
import org.mockito.MockitoAnnotations;

public class BoardTest {
    private int [][] dummyBoard;
    private Board board;

    private Play mockplay;
    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        mockplay = mock(Play.class);
        dummyBoard = new int[][]{{1, 1, 1, 1}, {1, 2, 3, 1}, {1, 4, 5, 1}, {1, 6, 7, 1}, {1, 1, 1, 1}};
        board = new Board(dummyBoard, mockplay);
    }
    @Test
    public void getHouses() {
        int expected = 1;
        ArrayList<House> actual = board.getHouses();
        assertEquals(expected, actual.size());
    }

    @Test
    public void getMeats() {
        int expected = 1;
        int falseExpected = 2;
        ArrayList<Meat> actual = board.getMeats();
        assertEquals(expected, actual.size());
        assertNotEquals(falseExpected, actual.size());
    }

    @Test
    public void getCoins() {
        int expected = 1;
        int falseExpected = 2;
        ArrayList<Coin> actual = board.getCoins();
        assertEquals(expected, actual.size());
        assertNotEquals(falseExpected, actual.size());
    }

    @Test
    public void getDynamites() {
        int expected = 1;
        int falseExpected = 0;
        ArrayList<Dynamite> actual = board.getDynamites();
        assertEquals(expected, actual.size());
        assertNotEquals(falseExpected, actual.size());
    }

    @Test
    public void getGoblins() {
        int expected = 1;
        int falseExpected = 4;
        ArrayList<Goblin> actual = board.getGoblins();
        assertEquals(expected, actual.size());
        assertNotEquals(falseExpected, actual.size());
    }

    @Test
    public void getKnights() {
        int expected = 1;
        int falseExpected = 2;
        ArrayList<Knight> actual = board.getKnights();
        assertEquals(expected, actual.size());
        assertNotEquals(falseExpected, actual.size());
    }

    @Test
    public void getTrees() {
        int expected = 14;
        int falseExpected = 1;
        ArrayList<Tree> actual = board.getTrees();
        assertEquals(expected, actual.size());
        assertNotEquals(falseExpected, actual.size());
    }

    @Test
    public void getCurrentLevel() {
        Board testBoard = new Board(ResourceLoader.LEVEL_TEST_PATH, mockplay);
        int[][] expected = testBoard.getCurrentLevel();
        int[][] actual = board.getCurrentLevel();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void isCellPassable() {
        int row = 0;
        int column = 0;
        boolean expected = true;
        boolean actualFalse = board.isCellPassable(row, column);
        boolean actual = board.isCellPassable(1, 1);
        assertEquals(expected, actual);
        assertNotEquals(expected, actualFalse);
    }

    @Test
    public void getBoardWidth() {
        int expected = 4;
        int actual = board.getBoardWidth();
        assertEquals(expected, actual);
    }

    @Test
    public void getBoardHeight() {
        int expected = 5;
        int actual = board.getBoardHeight();

        assertEquals(expected, actual);
    }

    @Test
    public void getBoardWidthPixels() {
        int expected = board.getBoardWidth()*125;
        int actual = board.getBoardWidthPixels();
        assertEquals(expected, actual);
    }

    @Test
    public void getBoardHeightPixels() {
        int expected = board.getBoardHeight()*125;
        int actual = board.getBoardHeightPixels();
        assertEquals(expected, actual);
    }

    @Test
    public void getCellSize() {
        int expected = 125;
        int actual = board.getCellSize();
        assertEquals(expected, actual);
    }
}
