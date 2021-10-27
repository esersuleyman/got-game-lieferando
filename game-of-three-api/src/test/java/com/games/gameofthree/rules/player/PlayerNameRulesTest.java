package com.games.gameofthree.rules.player;

import com.games.gameofthree.domain.Player;
import com.games.gameofthree.player.exceptions.PlayerCannotBeRegistredException;
import com.games.gameofthree.repository.PlayerRepository;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayerNameRulesTest {

	@InjectMocks
	private PlayerNameRules beanUnderTest;

	@Mock
	private PlayerRepository playerRepository;

	@Test
	public void should_checkThatPlayerNameIsNotUsed_throwException_whenPlayerNameIsAlreadyUsed() {
		// Given
		when(playerRepository.findAll()).thenReturn(asList(new Player("name")));

		// When
		ThrowingRunnable runnable = () -> beanUnderTest.checkThatPlayerNameIsNotUsed("name");

		// Then
		assertThrows("A new player cannot be registred beacause Player name is already used.",
				PlayerCannotBeRegistredException.class, runnable);
	}

	@Test
	public void should_checkThatPlayerNameIsNotUsed_doesntThrowException_whenPlayerNameIsNotUsed() {
		// Given
		when(playerRepository.findAll()).thenReturn(asList(new Player("name")));

		// When
		beanUnderTest.checkThatPlayerNameIsNotUsed("other_name");

	}

}
