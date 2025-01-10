package com.example.entities;

import java.awt.image.BufferedImage;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class GameImageTest {
    private GameImage gameImage;
    private BufferedImage bufferedImage;
    @BeforeEach
    void setup(){
        bufferedImage = mock(BufferedImage.class);
        gameImage = new GameImage(bufferedImage, 125, 125);
    }
	@Test
	public void getImage() {
		GameImage g = new GameImage(null, 123, 123);
		BufferedImage expected = null;
		BufferedImage actual = g.getImage();
		assertEquals(expected, actual);
	}

	@Test
	public void setImage() {
		BufferedImage temp = mock(BufferedImage.class);
		BufferedImage old = gameImage.getImage();
		gameImage.setImage(temp);
		BufferedImage newImage = gameImage.getImage();
		assertNotEquals(temp, old);
		assertEquals(temp, newImage);
	}

	@Test
	public void getImageWidth() {
		int expected = 125;
		int actual = gameImage.getImageWidth();
		assertEquals(expected, actual);
	}

	@Test
	public void setImageWidth() {
		int expected = 125;
		int actual = gameImage.getImageWidth();
		gameImage.setImageWidth(0);
		int newActual = gameImage.getImageWidth();
		assertEquals(expected, actual);
		assertNotEquals(expected, newActual);
	}

	@Test
	public void getImageHeight() {
		int expected = 125;
		int actual = gameImage.getImageHeight();
		assertEquals(expected, actual);
	}

	@Test
	public void setImageHeight() {
		int expected = 125;
		int actual = gameImage.getImageHeight();
		gameImage.setImageHeight(0);
		int newActual = gameImage.getImageHeight();
		assertEquals(expected, actual);
		assertNotEquals(expected, newActual);
	}
}
