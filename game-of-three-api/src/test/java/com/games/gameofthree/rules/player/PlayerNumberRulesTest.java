package com.games.gameofthree.rules.player;

import com.games.gameofthree.domain.Player;
import com.games.gameofthree.player.exceptions.PlayerCannotBeRegistredException;
import com.games.gameofthree.repository.PlayerRepository;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayerNumberRulesTest {

	private PlayerNumberRules beanUnderTest;

	@Mock
	private PlayerRepository playerRepository;

	@Test
	public void should_checkThatOtherPlayerIsRequired_throwException_whenNoOtherPlayIsRequired() {
		// Given
		beanUnderTest = new PlayerNumberRules(playerRepository, 2);
		when(playerRepository.findAll()).thenReturn(asList(new Player("name1"), new Player("name2")));

		// When
		ThrowingRunnable runnable = () -> beanUnderTest.checkThatOtherPlayerIsRequired();

		// Then
		assertThrows("A new player cannot be registred beacause Required player number is already achieved.",
				PlayerCannotBeRegistredException.class, runnable);
	}

	@Test
	public void should_checkThatOtherPlayerIsRequired_doesntThrowException_whenOtherPlayIsRequired() {
		// Given
		beanUnderTest = new PlayerNumberRules(playerRepository, 3);
		when(playerRepository.findAll()).thenReturn(asList(new Player("name1"), new Player("name2")));

		// When
		beanUnderTest.checkThatOtherPlayerIsRequired();
	}

	@Test
	public void should_isRequiredPlayerNumberAchieved_returnTrue_whenRequiredPlayerNumberIsAchieved() {
		// Given
		beanUnderTest = new PlayerNumberRules(playerRepository, 2);
		when(playerRepository.findAll()).thenReturn(asList(new Player("name1"), new Player("name2")));

		// When
		boolean isRequiredPlayerNumberAchieved = beanUnderTest.isRequiredPlayerNumberAchieved();

		// Then
		assertThat(isRequiredPlayerNumberAchieved).isTrue();
	}

	@Test
	public void should_isRequiredPlayerNumberAchieved_returnFalse_whenRequiredPlayerNumberIsNotAchieved() {
		// Given
		beanUnderTest = new PlayerNumberRules(playerRepository, 3);
		when(playerRepository.findAll()).thenReturn(asList(new Player("name1"), new Player("name2")));

		// When
		boolean isRequiredPlayerNumberAchieved = beanUnderTest.isRequiredPlayerNumberAchieved();

		// Then
		assertThat(isRequiredPlayerNumberAchieved).isFalse();
	}

}
