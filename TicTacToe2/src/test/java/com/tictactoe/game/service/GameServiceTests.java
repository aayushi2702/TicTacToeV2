package com.tictactoe.game.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.tictactoe.game.enumeration.Player;
import com.tictactoe.game.service.impl.GameBoard;
import com.tictcatoe.game.exception.InvalidTurnException;

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
		assertThat(gameService.playGame(Player.X, 0, 0).getCurrentPlayer()).isEqualTo(Player.X);
	}

	@Test(expected = InvalidTurnException.class)
	public void shouldThrowInvalidTurnExceptionIfOMovesFirst() {
		gameService.playGame(Player.O, 0, 1);
	}

	@Test
	public void getPositionFromPlayerAndSaveOnGameBoard() {
		gameService.playGame(Player.X, 0, 2);
		assertThat(gameBoard.getPlayerInPosition(0, 2)).isEqualTo(Player.X.getValue());
	}
}