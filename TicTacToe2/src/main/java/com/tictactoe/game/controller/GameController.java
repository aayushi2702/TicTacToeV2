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
import com.tictactoe.game.service.GameService;
import com.tictcatoe.game.exception.InvalidTurnException;

@RestController
public class GameController {

	@Autowired
	private GameService gameService;

	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@PostMapping(value = "/tictactoe/play/{player}/{row}/{column}")
	public ResponseEntity<String> playGameHandler(@PathVariable(name = "player") Player player,
			@PathVariable(name = "row") int row, @PathVariable(name = "column") int column) {
		return ResponseEntity.status(HttpStatus.OK).body(gameService.playGame(player, row, column));
	}

	@ExceptionHandler(value = InvalidTurnException.class)
	public ResponseEntity<ErrorResponse> handleInvalidTurnException(InvalidTurnException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(ex.getMessage()));
	}

}