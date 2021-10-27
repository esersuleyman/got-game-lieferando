package com.games.gameofthree.service.impl;

import com.games.gameofthree.domain.GameRound;
import com.games.gameofthree.domain.GameState;
import com.games.gameofthree.service.PlayerService;
import com.games.gameofthree.rules.game.GameRules;
import com.games.gameofthree.rules.player.PlayerNumberRules;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.games.gameofthree.domain.GameModeEnum.AI;
import static com.games.gameofthree.domain.GameModeEnum.HUMAN;
import static com.games.gameofthree.domain.GameStateEnum.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceImplTest {

	private GameServiceImpl beanUnderTest;

	@Mock
	private PlayerService playerService;

	@Mock
	private GameRules gameRules;

	@Mock
	private PlayerNumberRules playerNumberRules;

	@Test
	public void should_playRound_returnGameStateInProgress_whenItIsNotOver() {
		// Given
		beanUnderTest = new GameServiceImpl(playerService, gameRules, playerNumberRules, 30, 10);
		GameRound gameRound = new GameRound(10, "Rachid", -1, HUMAN);
		when(gameRules.isOver(3)).thenReturn(false);

		// When
		GameState gameState = beanUnderTest.playRound(gameRound);

		// Then
		assertThat(gameState.getCurrentNumber()).isEqualTo(3);
		assertThat(gameState.getLastNumberAdded()).isEqualTo(-1);
		assertThat(gameState.getLastPlayer()).isEqualTo("Rachid");
		assertThat(gameState.getState()).isEqualTo(IN_PROGRESS);
	}

	@Test
	public void should_playRound_returnGameStateOver_whenItIsOver() {
		// Given
		beanUnderTest = new GameServiceImpl(playerService, gameRules, playerNumberRules, 30, 10);
		GameRound gameRound = new GameRound(3, "Rachid", 0, AI);
		when(gameRules.isOver(1)).thenReturn(true);

		// When
		GameState gameState = beanUnderTest.playRound(gameRound);

		// Then
		assertThat(gameState.getCurrentNumber()).isEqualTo(1);
		assertThat(gameState.getLastNumberAdded()).isEqualTo(0);
		assertThat(gameState.getLastPlayer()).isEqualTo("Rachid");
		assertThat(gameState.getState()).isEqualTo(OVER);
	}

	@Test
	public void should_addNewPlayer_returnGameStateWaitingForPlayer_whenRequiredPlayerNumberIsNotAchieved() {
		// Given
		beanUnderTest = new GameServiceImpl(playerService, gameRules, playerNumberRules, 30, 10);
		when(playerNumberRules.isRequiredPlayerNumberAchieved()).thenReturn(false);

		// When
		GameState gameState = beanUnderTest.addNewPlayer("Rachid", HUMAN);

		// Then
		assertThat(gameState.getState()).isEqualTo(WAITING_FOR_PLAYER);
		assertThat(gameState.getCurrentNumber()).isNull();
		assertThat(gameState.getLastNumberAdded()).isNull();
		assertThat(gameState.getLastPlayer()).isEqualTo("Rachid");
	}

	@Test
	public void should_addNewPlayer_returnGameStateInProgress_whenRequiredPlayerNumberIsAchieved() {
		// Given
		beanUnderTest = new GameServiceImpl(playerService, gameRules, playerNumberRules, 30, 10);
		when(playerNumberRules.isRequiredPlayerNumberAchieved()).thenReturn(true);

		// When
		GameState gameState = beanUnderTest.addNewPlayer("Rachid", HUMAN);

		// Then
		assertThat(gameState.getState()).isEqualTo(IN_PROGRESS);
		assertThat(gameState.getCurrentNumber()).isNotNull();
		assertThat(gameState.getLastNumberAdded()).isNull();
		assertThat(gameState.getLastPlayer()).isEqualTo("Rachid");
	}

}
