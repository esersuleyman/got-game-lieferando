package com.games.gameofthree.controller;

import static com.games.gameofthree.helper.GameOfThreeHelper.addAiParticipant;
import static com.games.gameofthree.helper.GameOfThreeHelper.shouldAddAiPlayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.games.gameofthree.service.GameService;
import com.games.gameofthree.domain.GameState;
import com.games.gameofthree.dto.GameParticipateRequest;
import com.games.gameofthree.dto.GamePlayRoundRequest;
import com.games.gameofthree.service.PlayerService;

@Controller
public class GameOfThreeController {

	@Autowired
	private GameService gameService;

	@Autowired
	private PlayerService playerService;

	@Value("${gameofthree.player.ai-name}")
	private String aiPlayerName;

	@MessageMapping("/participate")
	@SendTo("/topic/gameOfThree")
	public GameState participate(GameParticipateRequest gameParticipateRequest) throws InterruptedException {
		GameState gameState = gameService.addNewPlayer(gameParticipateRequest.getPlayerName(),
				gameParticipateRequest.getGameMode());
		if (shouldAddAiPlayer(gameParticipateRequest, aiPlayerName)) {
			addAiParticipant(aiPlayerName);
		}
		return gameState;
	}

	@MessageMapping("/play")
	@SendTo("/topic/gameOfThree")
	public GameState play(GamePlayRoundRequest gamePlayRoundRequest) throws InterruptedException {
		return gameService.playRound(gamePlayRoundRequest.getGameRound());
	}

	@GetMapping("/isAPlayerWaiting")
	@ResponseBody
	public boolean isAPlayerWaiting() {
		return playerService.isAPlayerWaiting();
	}

}
