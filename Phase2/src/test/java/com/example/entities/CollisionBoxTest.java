package com.example.entities;

import com.example.utils.Position;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CollisionBoxTest {
    Graphics g;
    Position position;
    Dimension dimension;
    CollisionBox box;
    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        position = new Position(100, 100);
        dimension = new Dimension(100,100);
        g = mock(Graphics.class);
        box = new CollisionBox(position, dimension, 100, 100);
    }
	@Test
	public void getCollisionBox() {
		Rectangle2D.Float expected = new Rectangle2D.Float(100, 100, 100, 100);
		Rectangle2D.Float actual = box.getCollisionBox();
		assertEquals(expected, actual);
	}

	@Test
	public void getxRenderDifference() {
		int expected = 100;
		int actual = box.getxRenderDifference();
		assertEquals(expected, actual);
        assertNotEquals(99, box.getxRenderDifference());
	}

	@Test
	public void getyRenderDifference() {
        int expected = 100;
        int actual = box.getyRenderDifference();
        assertEquals(expected, actual);
        assertNotEquals(99, box.getxRenderDifference());
	}
	@Test
	public void getCurrentXPos() {
		float expected = 100;
		float actual = box.getCurrentXPos();
		assertEquals(expected, actual, 0.0000001F);
        assertNotEquals(50, actual, 0.000001F);
	}

	@Test
	public void getCurrentYPos() {
        float expected = 100;
        float actual = box.getCurrentXPos();
        assertEquals(expected, actual, 0.0000001F);
        assertNotEquals(50, actual, 0.000001F);
	}

	@Test
	public void setCurrentXPos() {
		float newX = 123F;
		box.setCurrentXPos(newX);
        assertEquals(newX, box.getCurrentXPos(), 0.00001f);
	}

	@Test
	public void setCurrentYPos() {
        float newY = 123F;
        box.setCurrentYPos(newY);
        assertEquals(newY, box.getCurrentYPos(), 0.00001f);
	}

	@Test
	public void getWidth() {
		float expected = 100;
		float actual = box.getWidth();
		assertEquals(expected, actual, 0.0000001F);
        assertNotEquals(120, actual, 0.00001f);
	}

	@Test
	public void getHeight() {
        float expected = 100;
        float actual = box.getHeight();
        assertEquals(expected, actual, 0.0000001F);
        assertNotEquals(120, actual, 0.00001f);
	}
}
