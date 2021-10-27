package com.games.gameofthree.rules.game;

import com.games.gameofthree.rules.player.PlayerNumberRules;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GameRulesTest {

	private GameRules beanUnderTest;

	@Mock
	private PlayerNumberRules playerNumberRules;

	@Test
	public void should_isOver_returnTrue_whenGameOverAtNumberIsAchieved() {
		// Given
		beanUnderTest = new GameRules(1, playerNumberRules);

		// When
		boolean isGameOver = beanUnderTest.isOver(1);

		// Then
		assertThat(isGameOver).isTrue();
	}

	@Test
	public void should_isOver_returnFalse_whenGameOverAtNumberIsNotYetAchieved() {
		// Given
		beanUnderTest = new GameRules(1, playerNumberRules);

		// When
		boolean isGameOver = beanUnderTest.isOver(2);

		// Then
		assertThat(isGameOver).isFalse();
	}

	public void should_isReady_returnTrue_whenRequiredPlayerNumberIsAchieved() {
		// Given
		Mockito.when(playerNumberRules.isRequiredPlayerNumberAchieved()).thenReturn(true);

		// When
		boolean isGameReady = beanUnderTest.isReady();

		// Then
		assertThat(isGameReady).isTrue();
	}

	public void should_isReady_returnFalse_whenRequiredPlayerNumberIsNotYetAchieved() {
		// Given
		Mockito.when(playerNumberRules.isRequiredPlayerNumberAchieved()).thenReturn(false);

		// When
		boolean isGameReady = beanUnderTest.isReady();

		// Then
		assertThat(isGameReady).isFalse();
	}

}
