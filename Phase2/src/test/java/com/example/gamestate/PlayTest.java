package com.example.gamestate;

import com.example.game.Game;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.example.entities.Knight;

import java.awt.*;

public class PlayTest {
	private final Play play = new Play(new Game());
	;
	private final Graphics mockGraphics = mock(Graphics.class);
	@Test
	public void canPlayerMove() {
		float potentialXPos = 0;
		float potentialYPos = 0;
		int playerWidth = 128;
		int playerHeight = 128;
		boolean expected = false;
		boolean actual = play.canPlayerMove(potentialXPos, potentialYPos, playerWidth, playerHeight);
		assertEquals(expected, actual);
	}

	@Test
	public void getPlayer() {
		assertNotNull(play.getPlayer());
	}

	@Test
	public void getBoardDimension() {
		Dimension expected = new Dimension(1250, 875);
		Dimension actual = play.getBoardDimension();
		assertEquals(expected, actual);
		expected = new Dimension(9, 9);
		assertNotEquals(expected, actual);
	}

	@Test
	public void reset() {
		Knight original = play.getPlayer();
		play.reset();
		Knight newKnight = play.getPlayer();
		assertNotEquals(original, newKnight);
	}
}
