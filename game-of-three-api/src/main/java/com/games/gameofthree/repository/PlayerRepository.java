package com.games.gameofthree.repository;

import com.games.gameofthree.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {

}
