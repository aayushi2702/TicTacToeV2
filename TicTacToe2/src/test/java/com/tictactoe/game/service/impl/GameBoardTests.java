package com.tictactoe.game.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.tictactoe.game.enumeration.Player;
import com.tictactoe.game.enumeration.Position;

@RunWith(MockitoJUnitRunner.class)
public class GameBoardTests {

	@Autowired
	private GameBoard gameBoard;

	@Before
	public void setUp() {
		gameBoard = new GameBoard();
		gameBoard.initializeGameBoard();
	}

	@Test
	public void shouldSaveInputValueOnGameBoard() {
		gameBoard.setPlayerInPosition(Position.TWO, Player.X);
		assertThat(gameBoard.getPlayerInPosition(Position.TWO)).isEqualTo(Player.X.getValue());
	}

	@Test
	public void getCountPositionsOccupiedOnGameBoard() {
		assertThat(gameBoard.getCountOfPositionsOccupied()).isZero();
	}

	@Test
	public void checkFirstRowOccupiedBySamePlayer() {
		gameBoard.setPlayerInPosition(Position.ONE, Player.X);
		gameBoard.setPlayerInPosition(Position.FIVE, Player.O);
		gameBoard.setPlayerInPosition(Position.TWO, Player.X);
		gameBoard.setPlayerInPosition(Position.NINE, Player.O);
		gameBoard.setPlayerInPosition(Position.THREE, Player.X);
		assertThat(gameBoard.isFirstRowOccupiedBySamePlayer()).isTrue();
	}

	@Test
	public void checkSecondRowOccupiedBySamePlayer() {
		gameBoard.setPlayerInPosition(Position.ONE, Player.X);
		gameBoard.setPlayerInPosition(Position.FOUR, Player.O);
		gameBoard.setPlayerInPosition(Position.THREE, Player.X);
		gameBoard.setPlayerInPosition(Position.FIVE, Player.O);
		gameBoard.setPlayerInPosition(Position.NINE, Player.X);
		gameBoard.setPlayerInPosition(Position.SIX, Player.O);
		assertThat(gameBoard.isSecondRowOccupiedBySamePlayer()).isTrue();
	}

	@Test
	public void checkThirdRowOccupiedBySamePlayer() {
		gameBoard.setPlayerInPosition(Position.SEVEN, Player.X);
		gameBoard.setPlayerInPosition(Position.FIVE, Player.O);
		gameBoard.setPlayerInPosition(Position.EIGHT, Player.X);
		gameBoard.setPlayerInPosition(Position.THREE, Player.O);
		gameBoard.setPlayerInPosition(Position.NINE, Player.X);
		assertThat(gameBoard.isThirdRowOccupiedBySamePlayer()).isTrue();
	}

	@Test
	public void checkFirstColumnOccupiedBySamePlayer() {
		gameBoard.setPlayerInPosition(Position.ONE, Player.X);
		gameBoard.setPlayerInPosition(Position.THREE, Player.O);
		gameBoard.setPlayerInPosition(Position.FOUR, Player.X);
		gameBoard.setPlayerInPosition(Position.FIVE, Player.O);
		gameBoard.setPlayerInPosition(Position.SEVEN, Player.X);
		assertThat(gameBoard.isFirstColumnOccupiedBySamePlayer()).isTrue();
	}

	@Test
	public void checkSecondColumnOccupiedBySamePlayer() {
		gameBoard.setPlayerInPosition(Position.TWO, Player.X);
		gameBoard.setPlayerInPosition(Position.THREE, Player.O);
		gameBoard.setPlayerInPosition(Position.FIVE, Player.X);
		gameBoard.setPlayerInPosition(Position.SEVEN, Player.O);
		gameBoard.setPlayerInPosition(Position.EIGHT, Player.X);
		assertThat(gameBoard.isSecondColumnOccupiedBySamePlayer()).isTrue();
	}

	@Test
	public void checkThirdColumnOccupiedBySamePlayer() {
		gameBoard.setPlayerInPosition(Position.TWO, Player.X);
		gameBoard.setPlayerInPosition(Position.THREE, Player.O);
		gameBoard.setPlayerInPosition(Position.FIVE, Player.X);
		gameBoard.setPlayerInPosition(Position.SIX, Player.O);
		gameBoard.setPlayerInPosition(Position.ONE, Player.X);
		gameBoard.setPlayerInPosition(Position.NINE, Player.O);
		assertThat(gameBoard.isThirdColumnOccupiedBySamePlayer()).isTrue();
	}

	@Test
	public void checkFirstDiagonalOccupiedBySamePlayer() {
		gameBoard.setPlayerInPosition(Position.ONE, Player.X);
		gameBoard.setPlayerInPosition(Position.TWO, Player.O);
		gameBoard.setPlayerInPosition(Position.FIVE, Player.X);
		gameBoard.setPlayerInPosition(Position.THREE, Player.O);
		gameBoard.setPlayerInPosition(Position.NINE, Player.X);
		assertThat(gameBoard.isFirstDiagonalOccupiedBySamePlayer()).isTrue();
	}

	@Test
	public void checkSecondDiagonalOccupiedBySamePlayer() {
		gameBoard.setPlayerInPosition(Position.TWO, Player.X);
		gameBoard.setPlayerInPosition(Position.THREE, Player.O);
		gameBoard.setPlayerInPosition(Position.SIX, Player.X);
		gameBoard.setPlayerInPosition(Position.FIVE, Player.O);
		gameBoard.setPlayerInPosition(Position.ONE, Player.X);
		gameBoard.setPlayerInPosition(Position.SEVEN, Player.O);
		assertThat(gameBoard.isSecondDiagonalOccupiedBySamePlayer()).isTrue();
	}

	@Test
	public void shouldDeclareGameAsTieIfAllPositionsAreFilled() {
		gameBoard.setPlayerInPosition(Position.ONE, Player.X);
		gameBoard.setPlayerInPosition(Position.THREE, Player.O);
		gameBoard.setPlayerInPosition(Position.TWO, Player.X);
		gameBoard.setPlayerInPosition(Position.FIVE, Player.O);
		gameBoard.setPlayerInPosition(Position.SEVEN, Player.X);
		gameBoard.setPlayerInPosition(Position.EIGHT, Player.O);
		gameBoard.setPlayerInPosition(Position.NINE, Player.X);
		gameBoard.setPlayerInPosition(Position.FOUR, Player.O);
		gameBoard.setPlayerInPosition(Position.SIX, Player.X);
		assertThat(gameBoard.isBoardFull()).isTrue();
	}
}