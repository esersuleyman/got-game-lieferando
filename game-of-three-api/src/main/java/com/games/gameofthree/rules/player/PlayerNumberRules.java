package com.games.gameofthree.rules.player;

import com.games.gameofthree.domain.Player;
import com.games.gameofthree.player.exceptions.PlayerCannotBeRegistredException;
import com.games.gameofthree.repository.PlayerRepository;

import java.util.List;

import static com.games.gameofthree.player.exceptions.PlayerCannotBeRegistredReason.REQUIRED_PLAYER_NUMBER_ACHIEVED;
import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * Policy class containing player number rules
 *
 */
public class PlayerNumberRules {

	private PlayerRepository playerRepository;

	private int requiredPlayerNumberToStart;

	public PlayerNumberRules(PlayerRepository playerRepository, int requiredPlayerNumberToStart) {
		this.playerRepository = playerRepository;
		this.requiredPlayerNumberToStart = requiredPlayerNumberToStart;
	}

	public void checkThatOtherPlayerIsRequired() {
		if (isRequiredPlayerNumberAchieved()) {
			throw new PlayerCannotBeRegistredException(REQUIRED_PLAYER_NUMBER_ACHIEVED);
		}
	}

	public boolean isRequiredPlayerNumberAchieved() {
		List<Player> playerAlreadyRegistred = playerRepository.findAll();
		return !isEmpty(playerAlreadyRegistred) && playerAlreadyRegistred.size() >= requiredPlayerNumberToStart;
	}

}
