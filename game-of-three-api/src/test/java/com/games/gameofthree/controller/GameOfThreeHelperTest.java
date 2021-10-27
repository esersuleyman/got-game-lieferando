package com.games.gameofthree.controller;

import static com.games.gameofthree.domain.GameModeEnum.AI;
import static com.games.gameofthree.domain.GameModeEnum.HUMAN;
import static org.assertj.core.api.Assertions.assertThat;

import com.games.gameofthree.helper.GameOfThreeHelper;
import org.junit.jupiter.api.Test;

import com.games.gameofthree.dto.GameParticipateRequest;

public class GameOfThreeHelperTest {

	@Test
	public void should_shouldAddAiPlayer_returnTrue_whenGameModeIsAiAndPlayerNameIsNotAi() {
		// Given
		GameParticipateRequest gameParticipateRequest = new GameParticipateRequest("Rachid", AI);
		String aiPlayerName = "Ai";

		// When
		boolean shouldAddAiPlayer = GameOfThreeHelper.shouldAddAiPlayer(gameParticipateRequest, aiPlayerName);

		// Then
		assertThat(shouldAddAiPlayer).isTrue();
	}

	@Test
	public void should_shouldAddAiPlayer_returnFalse_whenGameModeIsAiAndPlayerNameIsAi() {
		// Given
		String aiPlayerName = "Ai";
		GameParticipateRequest gameParticipateRequest = new GameParticipateRequest(aiPlayerName, AI);

		// When
		boolean shouldAddAiPlayer = GameOfThreeHelper.shouldAddAiPlayer(gameParticipateRequest, aiPlayerName);

		// Then
		assertThat(shouldAddAiPlayer).isFalse();
	}

	@Test
	public void should_shouldAddAiPlayer_returnFalse_whenGameModeIsHuman() {
		// Given
		String aiPlayerName = "Ai";
		GameParticipateRequest gameParticipateRequest = new GameParticipateRequest("Rachid", HUMAN);

		// When
		boolean shouldAddAiPlayer = GameOfThreeHelper.shouldAddAiPlayer(gameParticipateRequest, aiPlayerName);

		// Then
		assertThat(shouldAddAiPlayer).isFalse();
	}

}
