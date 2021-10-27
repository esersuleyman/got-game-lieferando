package com.games.gameofthree.service;

import com.games.gameofthree.domain.Player;

public interface PlayerService {

	void add(Player player);

	void reinitPlayers();

	boolean isAPlayerWaiting();
}
