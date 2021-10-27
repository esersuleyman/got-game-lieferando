package com.games.gameofthree.rules.game;

import com.games.gameofthree.rules.player.PlayerNumberRules;

/**
 * Policy Class containing the rules of the game
 * 
 *
 */
public class GameRules {

	private int gameOverAtNumber;

	private PlayerNumberRules playerNumberRules;

	public GameRules(int gameOverAtNumber, PlayerNumberRules playerNumberRules) {
		this.gameOverAtNumber = gameOverAtNumber;
		this.playerNumberRules = playerNumberRules;
	}

	public boolean isOver(int currentNumber) {
		return currentNumber == gameOverAtNumber;
	}

	public boolean isReady() {
		return playerNumberRules.isRequiredPlayerNumberAchieved();
	}

}
