package com.example.entities;

import com.example.entities.GameEntity;
import com.example.entities.GameImage;
import com.example.utils.EntityStates;
import com.example.utils.Position;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GameEntityTest {

    @Test
    void testState() {
        GameEntity entity = new TestGameEntity(new Position(0, 0), new Dimension(10, 10), null, EntityStates.IDLE);
        assertEquals(EntityStates.IDLE, entity.getState());

        entity.setState(EntityStates.MOVING);
        assertEquals(EntityStates.MOVING, entity.getState());
    }

    @Test
    void testCurrentPosition() {
        Position initialPosition = new Position(0, 0);
        GameEntity entity = new TestGameEntity(initialPosition, new Dimension(10, 10), null, EntityStates.IDLE);
        assertEquals(initialPosition, entity.getCurrentPosition());

        Position newPosition = new Position(20, 30);
        entity.setCurrentPosition(newPosition);
        assertEquals(newPosition, entity.getCurrentPosition());
    }

    @Test
    void testWidthAndHeight() {
        Dimension dimension = new Dimension(10, 20);
        GameEntity entity = new TestGameEntity(new Position(0, 0), dimension, null, EntityStates.IDLE);
        assertEquals(10, entity.getWidth());
        assertEquals(20, entity.getHeight());
    }

    @Test
    void testInitializeCollisionBox() {
        TestGameEntity entity = new TestGameEntity(new Position(0, 0), new Dimension(10, 10), null, EntityStates.IDLE);
        entity.initializeCollisionBox(); // No assertion, as it's testing an abstract method
        // Here you can add assertions related to collision box initialization if needed
    }

    @Test
    void testRender() {
        Graphics mockedGraphics = Mockito.mock(Graphics.class);
        TestGameEntity entity = new TestGameEntity(new Position(0, 0), new Dimension(10, 10), null, EntityStates.IDLE);
        entity.render(mockedGraphics, 0, 0);
        // Here you can add assertions related to rendering if needed
    }

    // Inner class for testing, extends GameEntity
    private static class TestGameEntity extends GameEntity {
        public TestGameEntity(Position position, Dimension dimension, GameImage image, EntityStates state) {
            super(position, dimension, image, state);
        }

        @Override
        protected void initializeCollisionBox() {

        }

        @Override
        public void render(Graphics g, int xOffset, int yOffset) {

        }

    }
}
