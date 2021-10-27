package com.games.gameofthree.service.impl;

import com.games.gameofthree.domain.Player;
import com.games.gameofthree.player.exceptions.PlayerCannotBeRegistredException;
import com.games.gameofthree.repository.PlayerRepository;
import com.games.gameofthree.rules.player.PlayerRules;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceImplTest {

	@InjectMocks
	private PlayerServiceImpl beanUnderTest;

	@Mock
	private PlayerRepository playerRepository;

	@Mock
	private PlayerRules playerRules;

	@Test
	public void should_add_savePlayer_whenThePlayerCanBeRegistred() {
		// Given
		Player player = new Player("Rachid");

		// When
		beanUnderTest.add(player);

		// Then
		verify(playerRules).checkThatThePlayerCanBeRegistred("Rachid");
		verify(playerRepository).save(player);
	}

	@Test
	public void should_add_throwException_whenThePlayerCannotBeRegistred() {
		// Given
		Player player = new Player("Rachid");
		doThrow(PlayerCannotBeRegistredException.class).when(playerRules).checkThatThePlayerCanBeRegistred("Rachid");

		// When
		ThrowingRunnable runnable = () -> beanUnderTest.add(player);

		// Then
		assertThrows(PlayerCannotBeRegistredException.class, runnable);
		verify(playerRepository, never()).save(player);
	}

	@Test
	public void should_reinitPlayers_deleteAllPlayers() {
		// When
		beanUnderTest.reinitPlayers();

		// Then
		verify(playerRepository).deleteAll();
	}

}
