package com.example.entities;

import com.example.utils.EntityStates;
import com.example.utils.Position;
import com.example.utils.ResourceLoader;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.mockito.Mockito.mock;

public class TreeTest {

    private GameImage gameImage;
    private Position position;
    private EntityStates state;
    private Dimension dimension;
    private Graphics mockGraphics;
    private Tree tree;
    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        mockGraphics = mock(Graphics.class);
        BufferedImage temp = null;
        try {
            temp = ResourceLoader.getImage(ResourceLoader.TREES2_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gameImage = new GameImage(temp, 128, 128);
        position = new Position(123.4F, 123.4F);
        dimension = new Dimension(128, 128);
        state = EntityStates.IDLE;
        tree = new Tree(position, dimension, gameImage, state);
    }
    @Test
    public void renderTest() {
        int xOffset = 125;
        int yOffset = 125;
        tree.render(mockGraphics, xOffset, yOffset);
    }
}