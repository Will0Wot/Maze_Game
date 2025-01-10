package com.example.game;

import com.example.gamestate.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.example.entities.Knight;

import java.awt.Graphics;

public class GameTest {
	private Game game;
	@BeforeEach
	void setup(){
		game = new Game();
	}

	@Test
	public void getScreenWidthTest() {
		int expected = 0;
		int actual = game.getScreenWidth();
		assertNotEquals(expected, actual);
	}

	@Test
	public void getScreenHeightTest() {
		int expected = 0;
		int actual = game.getScreenHeight();
		assertNotEquals(expected, actual);
	}

	@Test
	public void getPlayerTest() {
		Knight actual = game.getPlayer();
		assertNotNull(actual);
	}

	@Test
	public void getCurrentStateTest() {
		State expected = game.getState(Gamestate.MENU);
		State actual = game.getCurrentState();
		assertEquals(expected, actual);
	}

	@Test
	public void resetMapTest() {
		game.resetMap();
		getPlayerTest();
		getScreenHeightTest();
		getScreenWidthTest();
		State expected = game.getState(Gamestate.PLAYING);
		State actual = game.getCurrentState();
		assertEquals(expected, actual);
	}

	@Test
	public void setCurrentStateTest() {
		State mock = game.getCurrentState();
		game.setCurrentState(Gamestate.PLAYING);
		assertNotEquals(mock, game.getCurrentState());
	}

	@Test
	public void isCurrentStateTest() {
		Gamestate state = Gamestate.MENU;
		boolean expected = true;
		boolean actual = game.isCurrentState(state);
		assertEquals(expected, actual);
	}
}
