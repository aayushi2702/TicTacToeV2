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
import com.tictcatoe.game.exception.InvalidPositionException;
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

	@Test(expected = InvalidPositionException.class)
	public void shouldThrowInvalidPositionExceptionIfInputPositionIsNotInRangeOf1to9() {
		gameService.playGame(Player.X, Position.DEFAULT.getValue());
	}

	@Test
	public void shouldDeclareWinnerIfAnyOneOfThreeRowsIsFilledBySamePlayer() {
		gameService.playGame(Player.X, Position.THREE.getValue());
		gameService.playGame(Player.O, Position.FOUR.getValue());
		gameService.playGame(Player.X, Position.TWO.getValue());
		gameService.playGame(Player.O, Position.NINE.getValue());
		assertThat(gameService.playGame(Player.X, Position.ONE.getValue()).getStatus()).isEqualTo("GAME_OVER");
	}

	@Test
	public void shouldDeclareWinnerIfAnyOneOfThreeColumnsIsFilledBySamePlayer() {
		gameService.playGame(Player.X, Position.TWO.getValue());
		gameService.playGame(Player.O, Position.THREE.getValue());
		gameService.playGame(Player.X, Position.ONE.getValue());
		gameService.playGame(Player.O, Position.SIX.getValue());
		gameService.playGame(Player.X, Position.FOUR.getValue());
		assertThat(gameService.playGame(Player.O, Position.NINE.getValue()).getStatus()).isEqualTo("GAME_OVER");
	}

	@Test
	public void shouldDeclareWinnerIfAnyOneOfTwoDiagonalIsFilledBySamePlayer() {
		gameService.playGame(Player.X, Position.ONE.getValue());
		gameService.playGame(Player.O, Position.THREE.getValue());
		gameService.playGame(Player.X, Position.FIVE.getValue());
		gameService.playGame(Player.O, Position.SIX.getValue());
		assertThat(gameService.playGame(Player.X, Position.NINE.getValue()).getStatus()).isEqualTo("GAME_OVER");
	}

	@Test
	public void shouldDeclareDrawIfAllPositionsAreFilledWithoutAnyPlayerFillingConsecutiveRowsColumnsOrDiagonals() {
		gameService.playGame(Player.X, Position.ONE.getValue());
		gameService.playGame(Player.O, Position.THREE.getValue());
		gameService.playGame(Player.X, Position.TWO.getValue());
		gameService.playGame(Player.O, Position.FIVE.getValue());
		gameService.playGame(Player.X, Position.SEVEN.getValue());
		gameService.playGame(Player.O, Position.EIGHT.getValue());
		gameService.playGame(Player.X, Position.NINE.getValue());
		gameService.playGame(Player.O, Position.FOUR.getValue());
		assertThat(gameService.playGame(Player.X, Position.SIX.getValue()).getResult()).isEqualTo("Game Draw");
	}

	@Test
	public void resetGameOnceWinnerIsIdentified() {
		gameService.playGame(Player.X, Position.TWO.getValue());
		gameService.playGame(Player.O, Position.THREE.getValue());
		gameService.playGame(Player.X, Position.ONE.getValue());
		gameService.playGame(Player.O, Position.SIX.getValue());
		gameService.playGame(Player.X, Position.FOUR.getValue());
		assertThat(gameService.playGame(Player.O, Position.NINE.getValue()).getStatus()).isEqualTo("GAME_OVER");
		assertThat(gameService.playGame(Player.X, Position.ONE.getValue()).getStatus()).isEqualTo("GAME_IN_PROGRESS");
	}

	@Test
	public void resetGameAfterDraw() {
		gameService.playGame(Player.X, Position.ONE.getValue());
		gameService.playGame(Player.O, Position.THREE.getValue());
		gameService.playGame(Player.X, Position.TWO.getValue());
		gameService.playGame(Player.O, Position.FIVE.getValue());
		gameService.playGame(Player.X, Position.SEVEN.getValue());
		gameService.playGame(Player.O, Position.EIGHT.getValue());
		gameService.playGame(Player.X, Position.NINE.getValue());
		gameService.playGame(Player.O, Position.FOUR.getValue());
		assertThat(gameService.playGame(Player.X, Position.SIX.getValue()).getResult()).isEqualTo("Game Draw");
		assertThat(gameService.playGame(Player.X, Position.ONE.getValue()).getStatus()).isEqualTo("GAME_IN_PROGRESS");
	}

	@Test
	public void getCountPositionOccupiedOnGameBoardIsZerowhenGameIsReset() {
		gameService.resetGame();
		assertThat(gameBoard.getCountOfPositionsOccupied()).isZero();
	}
}