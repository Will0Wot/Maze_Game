package com.example.entities;


import com.example.gamestate.Play;
import com.example.utils.EntityStates;
import com.example.utils.Position;
import com.example.utils.ResourceLoader;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class DynamiteTest {
    private GameImage gameImage;
    Position position;
    Dimension dimension;
    private Dynamite dynamite;
    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        BufferedImage image;
        try {
            image = ResourceLoader.getImage(ResourceLoader.TNT_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gameImage = new GameImage(image, Meat.MEAT_SIZE, Meat.MEAT_SIZE);
        dimension = new Dimension(Meat.MEAT_SIZE, Meat.MEAT_SIZE);
        position = new Position(125, 125);
        dynamite = new Dynamite(position, dimension, EntityStates.IDLE, gameImage);
    }


    @Test
    public void getInteractableAmount() {
        int expected = -25;
        int actual = dynamite.getInteractableAmount();
        assertEquals(expected, actual);
    }


    @Test
    public void interact() {
        BufferedImage knightImage;
        try {
            knightImage = ResourceLoader.getImage(ResourceLoader.KNIGHT_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GameImage image = new GameImage(knightImage, 125, 125);
        Knight knight = new Knight(position, dimension, EntityStates.IDLE, gameImage, mock(Play.class));
        int expected = 0;
        int actual = knight.getScore();
        assertEquals(expected, actual);
        dynamite.interact(knight);
        actual = knight.getScore();
        assertNotEquals(expected, actual);
    }
}
