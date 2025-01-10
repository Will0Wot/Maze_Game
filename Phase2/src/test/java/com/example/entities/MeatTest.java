package com.example.entities;

import com.example.utils.EntityStates;
import com.example.utils.Position;
import com.example.utils.ResourceLoader;
import org.junit.jupiter.api.*;
import com.example.gamestate.Play;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MeatTest {

    private Graphics mockGraphics;
    private GameImage gameImage;
    Position position;
    Dimension dimension;
    private Meat meat;
    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        mockGraphics = mock(Graphics.class);
        BufferedImage image;
        try {
            image = ResourceLoader.getImage(ResourceLoader.MEAT_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gameImage = new GameImage(image, Meat.MEAT_SIZE, Meat.MEAT_SIZE);
        dimension = new Dimension(Meat.MEAT_SIZE, Meat.MEAT_SIZE);
        position = new Position(123.4F, 123.4F);
        meat = new Meat(position, dimension, gameImage, EntityStates.IDLE);
    }

    @Test
    public void renderTest() {
        int xOffset = 123;
        int yOffset = 123;
        meat.render(mockGraphics, xOffset, yOffset);
    }

    @Test
    public void interactTest() {
        Knight test = makeKnight();
        int original = meat.getInteractableAmount();
        meat.interact(test);
        int actual = test.getScore();
        meat.interact(test);
        int notEqual =test.getScore();
        assertEquals(original, actual);
        assertNotEquals(original, notEqual);
    }

    @Test
    public void getInteractableAmountTest() {
        int expected = 25;
        int actual = meat.getInteractableAmount();
        assertEquals(expected, actual);
    }

    private Knight makeKnight(){
        BufferedImage temp;
        try {
            temp = ResourceLoader.getImage(ResourceLoader.KNIGHT_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GameImage image = new GameImage(temp, Knight.KNIGHT_SIZE, Knight.KNIGHT_SIZE);
        Play mockplay = mock(Play.class);
        return new Knight(position, dimension, EntityStates.IDLE, image, mockplay);
    }
}