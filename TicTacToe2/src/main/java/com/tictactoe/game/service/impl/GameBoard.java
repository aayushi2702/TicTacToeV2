package com.tictactoe.game.service.impl;

import java.nio.CharBuffer;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.tictactoe.game.enumeration.Player;
import com.tictactoe.game.enumeration.Position;

@Service
public class GameBoard {

	private char[][] board = new char[3][3];
	private static final int EMPTY_POSITION_ON_BOARD = 0;

	public void setPlayerInPosition(Position position, Player player) {
		board[position.getRow()][position.getColumn()] = player.getValue();
	}

	public char getPlayerInPosition(Position position) {
		return board[position.getRow()][position.getColumn()];
	}

	public int getCountOfPositionsOccupied() {
		return (int) Arrays.stream(board).map(CharBuffer::wrap).flatMapToInt(CharBuffer::chars)
				.filter(position -> position != EMPTY_POSITION_ON_BOARD).count();
	}

	public boolean isFirstRowOccupiedBySamePlayer() {
		if (getPlayerInPosition(Position.ONE) != EMPTY_POSITION_ON_BOARD) {
			return (getPlayerInPosition(Position.ONE) == getPlayerInPosition(Position.TWO)
					&& getPlayerInPosition(Position.TWO) == getPlayerInPosition(Position.THREE));
		}
		return false;
	}

	public boolean isSecondRowOccupiedBySamePlayer() {
		if (getPlayerInPosition(Position.FOUR) != EMPTY_POSITION_ON_BOARD) {
			return (getPlayerInPosition(Position.FOUR) == getPlayerInPosition(Position.FIVE)
					&& getPlayerInPosition(Position.FIVE) == getPlayerInPosition(Position.SIX));
		}
		return false;
	}

	public boolean isThirdRowOccupiedBySamePlayer() {
		if (getPlayerInPosition(Position.SEVEN) != EMPTY_POSITION_ON_BOARD) {
			return (getPlayerInPosition(Position.SEVEN) == getPlayerInPosition(Position.EIGHT)
					&& getPlayerInPosition(Position.EIGHT) == getPlayerInPosition(Position.NINE));
		}
		return false;
	}

	public boolean isAnyOneOfThreeRowsOccupiedBySamePlayer() {
		return isFirstRowOccupiedBySamePlayer() || isSecondRowOccupiedBySamePlayer()
				|| isThirdRowOccupiedBySamePlayer();
	}

	public boolean isFirstColumnOccupiedBySamePlayer() {
		if (getPlayerInPosition(Position.ONE) != EMPTY_POSITION_ON_BOARD) {
			return (getPlayerInPosition(Position.ONE) == getPlayerInPosition(Position.FOUR)
					&& getPlayerInPosition(Position.FOUR) == getPlayerInPosition(Position.SEVEN));
		}
		return false;
	}
}