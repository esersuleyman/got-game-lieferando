package com.games.gameofthree.rules.player;

/**
 * Policy class containing player rules
 *
 */
public class PlayerRules {
	private PlayerNumberRules playerNumberRules;

	private PlayerNameRules playerNameRules;

	public PlayerRules(PlayerNumberRules playerNumberRules, PlayerNameRules playerNameRules) {
		this.playerNumberRules = playerNumberRules;
		this.playerNameRules = playerNameRules;
	}

	public void checkThatThePlayerCanBeRegistred(String playerName) {
		playerNumberRules.checkThatOtherPlayerIsRequired();

		playerNameRules.checkThatPlayerNameIsNotUsed(playerName);
	}

}
