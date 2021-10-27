package com.games.gameofthree.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

import static java.util.Objects.hash;

@Entity
public class Player {

	@Id
	private String name;

	public Player() {
	}

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(name, other.name);
	}

}
