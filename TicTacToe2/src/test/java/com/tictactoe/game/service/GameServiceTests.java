package com.tictactoe.game.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.tictactoe.game.enumeration.Player;
import com.tictactoe.game.enumeration.Position;
import com.tictactoe.game.service.impl.GameBoard;
import com.tictcatoe.game.exception.InvalidTurnException;
import com.tictcatoe.game.exception.PositionOccupiedException;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTests {

	@Autowired
	private GameService gameService;
	@Autowired
	private GameBoard gameBoard;

	@Before
	public void setUp() {
		gameBoard = new GameBoard();
		gameService = new GameService(gameBoard);
	}

	@Test
	public void playerXShouldAlwaysGoFirst() {
		assertThat(gameService.playGame(Player.X, Position.ONE.getValue()).getCurrentPlayer()).isEqualTo(Player.X);
	}

	@Test(expected = InvalidTurnException.class)
	public void shouldThrowInvalidTurnExceptionIfOMovesFirst() {
		gameService.playGame(Player.O, Position.TWO.getValue());
	}

	@Test
	public void getPositionFromPlayerAndSaveOnGameBoard() {
		gameService.playGame(Player.X, Position.THREE.getValue());
		assertThat(gameBoard.getPlayerInPosition(Position.THREE)).isEqualTo(Player.X.getValue());
	}

	@Test(expected = InvalidTurnException.class)
	public void shouldThrowInvalidTurnExceptionIfSamePlayerPlaysConsecutiveTurns() {
		gameService.playGame(Player.X, Position.THREE.getValue());
		gameService.playGame(Player.X, Position.TWO.getValue());
	}

	@Test(expected = PositionOccupiedException.class)
	public void shouldThrowPositionOccupiedExceptionIfPlayerPlaysOnAlreadyOccupiedPosition() {
		gameService.playGame(Player.X, Position.TWO.getValue());
		gameService.playGame(Player.O, Position.TWO.getValue());
	}
}