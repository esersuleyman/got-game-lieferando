package com.games.gameofthree.service;

import com.games.gameofthree.domain.GameModeEnum;
import com.games.gameofthree.domain.GameRound;
import com.games.gameofthree.domain.GameState;

public interface GameService {

	GameState playRound(GameRound gameRound);

	GameState addNewPlayer(String player, GameModeEnum gameMode);

}
