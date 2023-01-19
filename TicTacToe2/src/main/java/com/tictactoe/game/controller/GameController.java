package com.tictactoe.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tictactoe.game.enumeration.Player;
import com.tictactoe.game.response.ErrorResponse;
import com.tictactoe.game.response.GameResponse;
import com.tictactoe.game.service.GameService;
import com.tictcatoe.game.exception.InvalidPositionException;
import com.tictcatoe.game.exception.InvalidTurnException;
import com.tictcatoe.game.exception.PositionOccupiedException;

@RestController
public class GameController {

	@Autowired
	private GameService gameService;

	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@PostMapping(value = "/tictactoe/play/{player}/{position}")
	public ResponseEntity<GameResponse> playGameHandler(@PathVariable(name = "player") Player player,
			@PathVariable(name = "position") int position) {
		return ResponseEntity.status(HttpStatus.OK).body(gameService.playGame(player, position));
	}

	@ExceptionHandler(value = InvalidTurnException.class)
	public ResponseEntity<ErrorResponse> handleInvalidTurnException(InvalidTurnException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(ex.getMessage()));
	}

	@ExceptionHandler(value = PositionOccupiedException.class)
	public ResponseEntity<ErrorResponse> handlePositionOccupiedException(PositionOccupiedException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(ex.getMessage()));
	}

	@ExceptionHandler(value = InvalidPositionException.class)
	public ResponseEntity<ErrorResponse> handleInvalidPositionException(InvalidPositionException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(ex.getMessage()));
	}

}