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

public class CoinTest {

    private Graphics mockGraphics;
    private GameImage gameImage;
    private Position position;
    private Dimension dimension;
    private Coin coin;
    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        mockGraphics = mock(Graphics.class);
        BufferedImage image;
        try {
            image = ResourceLoader.getImage(ResourceLoader.GOLD_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gameImage = new GameImage(image, Coin.COIN_SIZE, Coin.COIN_SIZE);
        dimension = new Dimension(Coin.COIN_SIZE, Coin.COIN_SIZE);
        position = new Position(123.4F, 123.4F);
        coin =  new Coin(position, dimension, gameImage, EntityStates.IDLE);
    }
    @Test
    public void renderTest() {
        int xOffset = 123;
        int yOffset = 123;
        coin.render(mockGraphics, xOffset, yOffset);
    }

    @Test
    public void interactTest() {
        Play mockPlay = mock(Play.class);
        BufferedImage image = null;
        try {
            image = ResourceLoader.getImage(ResourceLoader.KNIGHT_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GameImage mockImage = new GameImage(image, Knight.KNIGHT_SIZE, Knight.KNIGHT_SIZE);
        Knight knight = new Knight(position, dimension, EntityStates.DEAD, mockImage, mockPlay);
        int expected = 50;
        coin.interact(knight);
        int actual = knight.getScore();
        assertEquals(expected, actual);

    }

    @Test
    public void getInteractableAmountTest() {
        int expected = 50;
        int actual = coin.getInteractableAmount();
        assertEquals(expected, actual);
    }
}