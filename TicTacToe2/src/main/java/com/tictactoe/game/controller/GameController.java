package com.tictactoe.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tictactoe.game.enumeration.Player;
import com.tictactoe.game.response.GameResponse;
import com.tictactoe.game.service.GameService;

@RestController
@RequestMapping("/tictactoe")
public class GameController {

	@Autowired
	private GameService gameService;

	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@PostMapping(value = "/play/{player}/{position}")
	public ResponseEntity<GameResponse> playGameHandler(@PathVariable(name = "player") Player player,
			@PathVariable(name = "position") int position) {
		return ResponseEntity.status(HttpStatus.OK).body(gameService.playGame(player, position));
	}

	@PutMapping(value = "/resetgame")
	public ResponseEntity<String> resetGameHandler() {
		return ResponseEntity.status(HttpStatus.OK).body(gameService.resetGame());
	}

}