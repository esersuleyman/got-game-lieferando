package com.games.gameofthree.ai;

import static com.games.gameofthree.domain.GameModeEnum.AI;
import static java.util.Arrays.asList;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import com.games.gameofthree.domain.GameState;
import com.games.gameofthree.domain.GameStateEnum;
import com.games.gameofthree.dto.GameParticipateRequest;
import com.games.gameofthree.dto.GamePlayRoundRequest;

public class AiStompSessionHandler implements StompSessionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AiStompSessionHandler.class);

	private String aiPlayerName;

	public AiStompSessionHandler(String aiPlayerName) {
		this.aiPlayerName = aiPlayerName;
	}

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		// Subscribing Ai to Game Of Three Websocket
		session.subscribe("/topic/gameOfThree", new StompFrameHandler() {

			@Override
			public void handleFrame(StompHeaders headers, Object payload) {
				GameState gameState = (GameState) payload;
				// If the game is over, Ai should be disconnected from the websocket
				if (GameStateEnum.OVER == gameState.getState()) {
					session.disconnect();
				}
				// Here it is Ai round, so it submit an input number
				else if (!aiPlayerName.equals(gameState.getLastPlayer())) {
					session.send("/app/play", new GamePlayRoundRequest(gameState.getCurrentNumber(), aiPlayerName,
							countNumberToAdd(gameState.getCurrentNumber()), AI));
				}
			}

			@Override
			public Type getPayloadType(StompHeaders headers) {
				return GameState.class;
			}
		});
		// Ai send a request to participate in the game
		session.send("/app/participate", new GameParticipateRequest(aiPlayerName, AI));
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
	}

	@Override
	public Type getPayloadType(StompHeaders headers) {
		return GameState.class;
	}

	@Override
	public void handleTransportError(StompSession session, Throwable exception) {
		LOGGER.error(exception.getMessage(), exception);
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
			Throwable exception) {
		LOGGER.error(exception.getMessage(), exception);
	}

	private Integer countNumberToAdd(Integer currentNumber) {
		return asList(-1, 0, 1).stream().filter(number -> (currentNumber + number) % 3 == 0).findFirst().get();
	}
}
