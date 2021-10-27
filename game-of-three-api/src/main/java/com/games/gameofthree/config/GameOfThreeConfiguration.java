package com.games.gameofthree.config;

import com.games.gameofthree.service.GameService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.games.gameofthree.service.impl.GameServiceImpl;
import com.games.gameofthree.service.PlayerService;
import com.games.gameofthree.service.impl.PlayerServiceImpl;
import com.games.gameofthree.repository.PlayerRepository;
import com.games.gameofthree.rules.game.GameRules;
import com.games.gameofthree.rules.player.PlayerNameRules;
import com.games.gameofthree.rules.player.PlayerNumberRules;
import com.games.gameofthree.rules.player.PlayerRules;

@Configuration
public class GameOfThreeConfiguration {

	@Bean
	public GameService gameService(PlayerService playerService, GameRules gameRules,
								   PlayerNumberRules playerNumberRules, @Value("${gameofthree.game.min-number:2}") int minNumber,
								   @Value("${gameofthree.game.max-number:1000}") int maxNumber) {
		return new GameServiceImpl(playerService, gameRules, playerNumberRules, maxNumber, minNumber);
	}

	@Bean
	public PlayerService playerService(PlayerRepository playerRepository, PlayerRules playerRules) {
		return new PlayerServiceImpl(playerRepository, playerRules);
	}

	@Bean
	public GameRules gameRules(@Value("${gameofthree.game.over-at-number:1}") int gameOverAtNumber,
								PlayerNumberRules playerNumberRules) {
		return new GameRules(gameOverAtNumber, playerNumberRules);
	}

	@Bean
	public PlayerRules playerRules(PlayerNumberRules playerNumberRules, PlayerNameRules playerNameRules) {
		return new PlayerRules(playerNumberRules, playerNameRules);
	}

	@Bean
	public PlayerNameRules playerNameRules(PlayerRepository playerRepository) {
		return new PlayerNameRules(playerRepository);
	}

	@Bean
	public PlayerNumberRules playerNumberPolicy(PlayerRepository playerRepository,
												@Value("${gameofthree.player.required-number:2}") int requiredPlayerNumberToStart) {
		return new PlayerNumberRules(playerRepository, requiredPlayerNumberToStart);
	}



}
