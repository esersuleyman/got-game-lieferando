package com.games.gameofthree.domain;

public class GameRound {

	private Integer currentNumber;

	private String player;

	private Integer inputNumber;

	private GameModeEnum gameMode;

	public GameRound(Integer currentNumber, String player, Integer inputNumber, GameModeEnum gameMode) {
		this.currentNumber = currentNumber;
		this.player = player;
		this.inputNumber = inputNumber;
		this.gameMode = gameMode;
	}

	public Integer getCurrentNumber() {
		return currentNumber;
	}

	public String getPlayer() {
		return player;
	}

	public Integer getInputNumber() {
		return inputNumber;
	}

	public GameModeEnum getGameMode() {
		return gameMode;
	}
}
