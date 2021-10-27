package com.games.gameofthree.service.impl;

import com.games.gameofthree.service.GameService;
import com.games.gameofthree.domain.*;
import com.games.gameofthree.service.PlayerService;
import com.games.gameofthree.rules.game.GameRules;
import com.games.gameofthree.rules.player.PlayerNumberRules;

import java.util.Random;

import static com.games.gameofthree.domain.GameStateEnum.*;

/**
 * Class manager of the game
 *
 */
public class GameServiceImpl implements GameService {

	private PlayerService playerService;

	private GameRules gameRules;

	private PlayerNumberRules playerNumberRules;

	private int maxNumber;

	private int minNumber;

	public GameServiceImpl(PlayerService playerService, GameRules gameRules, PlayerNumberRules playerNumberRules,
						   int maxNumber, int minNumber) {
		this.playerService = playerService;
		this.gameRules = gameRules;
		this.playerNumberRules = playerNumberRules;
		this.maxNumber = maxNumber;
		this.minNumber = minNumber;
	}

	public GameState playRound(GameRound gameRound) {
		int newCurrentNumber = countCurrentNumber(gameRound);
		GameStateEnum state = getGameState(newCurrentNumber);
		if (state == OVER) {
			playerService.reinitPlayers();
		}
		return new GameState(newCurrentNumber, gameRound.getPlayer(), gameRound.getInputNumber(), state,
				gameRound.getGameMode());
	}

	public GameState addNewPlayer(String playerName, GameModeEnum gameMode) {
		playerService.add(new Player(playerName));
		if (playerNumberRules.isRequiredPlayerNumberAchieved()) {
			return new GameState(getARandomNumber(), playerName, IN_PROGRESS, gameMode);
		}
		return new GameState(playerName, WAITING_FOR_PLAYER, gameMode);
	}

	private GameStateEnum getGameState(int currentNumber) {
		if (gameRules.isOver(currentNumber)) {
			return OVER;
		}
		return IN_PROGRESS;
	}

	private int countCurrentNumber(GameRound gameRound) {
		return (gameRound.getCurrentNumber() + gameRound.getInputNumber()) / 3;
	}

	private int getARandomNumber() {
		return new Random().ints(minNumber, maxNumber).findFirst().getAsInt();
	}

}
