package com.example.entities;


import com.example.ai.Pathfinder;
import com.example.gamestate.Play;
import com.example.levels.Board;
import com.example.utils.EntityStates;
import com.example.utils.Position;
import com.example.utils.ResourceLoader;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;


import java.awt.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


public class GoblinTest {
    private Knight knight;
    private Goblin goblin;
    private Graphics mockGraphics;
    private Board board;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        mockGraphics = mock(Graphics.class);
        Play mockPLay = mock(Play.class);
        board = new Board(ResourceLoader.getLevel(ResourceLoader.LEVEL_TEST_PATH), mockPLay);
        knight = board.getKnights().get(0);
        goblin = board.getGoblins().get(0);
    }
    @Test
    public void update() {
        float x = goblin.getCurrentPosition().getX();
        float y = goblin.getCurrentPosition().getY();
        goblin.setEnraged(true);
        goblin.update();
        assertNotEquals(x, goblin.getCurrentPosition().getX());
        assertNotEquals(y, goblin.getCurrentPosition().getY());
        // test if it gets near player
        for (int i = 0; i < 1000; i++) {
            goblin.setEnraged(true);
            goblin.update();
        }
        assertNotEquals(x, goblin.getCurrentPosition().getX());
        assertNotEquals(y, goblin.getCurrentPosition().getY());
    }


    @Test
    public void isEnraged() {
        boolean expected = false;
        boolean actual = goblin.isEnraged();
        assertEquals(expected, actual);
    }


    @Test
    public void setEnraged() {
        boolean enraged = true;
        goblin.setEnraged(true);
        assertEquals(enraged, goblin.isEnraged());
    }


    @Test
    public void interact() {
        int expected = Integer.MIN_VALUE;
        goblin.interact(knight);
        assertEquals(expected, knight.getScore());
    }


    @Test
    public void getInteractableAmount() {
        int expected = Integer.MIN_VALUE;
        int actual = goblin.getInteractableAmount();
        assertEquals(expected, actual);
    }
}
