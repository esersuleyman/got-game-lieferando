package com.games.gameofthree.service.impl;

import com.games.gameofthree.domain.Player;
import com.games.gameofthree.service.PlayerService;
import com.games.gameofthree.repository.PlayerRepository;
import com.games.gameofthree.rules.player.PlayerRules;

/**
 * Class manager of the player model
 *
 */
public class PlayerServiceImpl implements PlayerService {

	private PlayerRepository playerRepository;

	private PlayerRules playerRules;

	public PlayerServiceImpl(PlayerRepository playerRepository, PlayerRules playerRules) {
		this.playerRepository = playerRepository;
		this.playerRules = playerRules;
	}

	@Override
	public void add(Player player) {
		playerRules.checkThatThePlayerCanBeRegistred(player.getName());

		playerRepository.save(player);
	}

	@Override
	public void reinitPlayers() {
		playerRepository.deleteAll();
	}

	public boolean isAPlayerWaiting() {
		return playerRepository.findAll().size() == 1;
	}

}
