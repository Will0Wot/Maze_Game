package com.example.entities;

import com.example.entities.GameImage;
import com.example.entities.House;
import com.example.utils.EntityStates;
import com.example.utils.Position;
import com.example.utils.ResourceLoader;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HouseTest {

    @Test
    void testInitialization() {
        Position position = new Position(100, 100);
        Dimension dimension = new Dimension(128, 192);
        EntityStates state = EntityStates.IDLE;

        BufferedImage dummyImage = null;
        try {
            dummyImage = ResourceLoader.getImage(ResourceLoader.TREES2_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GameImage gameImage = new GameImage(dummyImage, 128, 192);
        House house = new House(position, dimension, gameImage, state);
        assertNotNull(house, "House instance should not be null");
        assertNotNull(house.getCollisionBox(), "Collision box should be initialized");
        assertEquals(state, house.getState(), "State should be initialized correctly");
    }

    @Test
    void testRendering() {
        Position position = new Position(100, 100);
        Dimension dimension = new Dimension(128, 192);
        EntityStates state = EntityStates.IDLE;

        // Create a dummy GameImage object for testing
        BufferedImage dummyImage = null;
        try {
            dummyImage = ResourceLoader.getImage(ResourceLoader.TREES2_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Graphics dummyGraphics = dummyImage.getGraphics();
        GameImage gameImage = new GameImage(dummyImage, 128, 192);

        House house = new House(position, dimension, gameImage, state);

        // Test rendering
        house.render(dummyGraphics, 0, 0);

        // Assert that rendering does not throw exceptions
        // (No direct assertions on rendering output as it depends on BufferedImage)
    }

    @Test
    void testInitializeCollisionBox() {
        Position position = new Position(100, 100);
        Dimension dimension = new Dimension(128, 192);
        EntityStates state = EntityStates.IDLE;

        // Create a dummy GameImage object for testing
        BufferedImage dummyImage = null;
        try {
            dummyImage = ResourceLoader.getImage(ResourceLoader.TREES2_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GameImage gameImage = new GameImage(dummyImage, 128, 192);

        House house = new House(position, dimension, gameImage, state);
        house.getCollisionBox();
    }
}