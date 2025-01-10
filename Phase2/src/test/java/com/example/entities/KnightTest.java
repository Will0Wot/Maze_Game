package com.example.entities;

import com.example.game.Game;
import com.example.gamestate.Play;
import com.example.utils.EntityStates;
import com.example.utils.Position;
import com.example.utils.ResourceLoader;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.utils.Directions;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class KnightTest {
	private Knight knight;
	private Position position;
	private Dimension dimension;
	private Play mockplay = mock(Play.class);
	private Play play = new Play(new Game());
	private Graphics mockGraphics;
	private EntityStates states;
	private GameImage gameImage;

	@BeforeEach
	void setup(){
		MockitoAnnotations.openMocks(true);
		position = new Position(350, 350);
		dimension = new Dimension(Knight.KNIGHT_SIZE, Knight.KNIGHT_SIZE);
		mockGraphics = mock(Graphics.class);
		states = EntityStates.IDLE;
		BufferedImage image;
        try {
            image = ResourceLoader.getImage(ResourceLoader.KNIGHT_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gameImage = new GameImage(image, Knight.KNIGHT_SIZE, Knight.KNIGHT_SIZE);
		knight = new Knight(position, dimension, states, gameImage, mockplay);
	}

	@Test
	public void updateEntityPosition() {
		Knight realKnight = new Knight(position, dimension, states, gameImage, play);
		float y = realKnight.getCollisionBox().x;
		float x = realKnight.getCollisionBox().y;
		realKnight.setActiveDirection(Directions.RIGHT, true);
		realKnight.setState(EntityStates.MOVING);
		realKnight.update();
		float newY = realKnight.getCollisionBox().x;
		float newX = realKnight.getCollisionBox().y;
		assertNotEquals(y, newY, 0.000001f);
		realKnight.setActiveDirection(Directions.RIGHT, false);
		assertEquals(x, newX, 0.000001f);
	}

	@Test
	public void incrementMandatoryRewardCount() {
		int old = knight.getMandatoryRewardCount();
		knight.incrementMandatoryRewardCount();
		assertNotEquals(old, knight.getMandatoryRewardCount());
	}

	@Test
	public void getMandatoryRewardCount() {
		int expected = 0;
		int actual = knight.getMandatoryRewardCount();
		assertEquals(expected, actual);
	}

	@Test
	public void incrementScore() {
		int expected = 25;
		int actual = knight.getScore();
		assertNotEquals(expected, actual);
		knight.incrementScore(25);
		actual = knight.getScore();
		assertEquals(expected, actual);
	}

	@Test
	public void getScore() {
		int expected = 0;
		int actual = knight.getScore();
		assertEquals(expected, actual);
	}

	@Test
	public void isDead() {
		knight.incrementScore(-500);
		boolean expected = true;
		boolean actual = knight.isDead();
		assertEquals(expected, actual);
	}
}
