package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
	List<Player> players;
	int playerNum;
	Board board;

	public void init(int number, String name) {
		String playerName;
		players = new ArrayList<>();
		board = new Board();
		inputPlayerNumber(number);
		inputPlayersName(name);
	}

	public Player playGame(Player player, Turn frame) {
		player.rollingBall(frame);

		if (player.isOneMoreRollingBall(frame)) {
			player.rollingRestBall(frame);
			player.calculateResultScore();
			return player;
		}
		return player;
	}

	private void inputPlayersName(String name) {
		String[] names = name.split(",");
		for (int i = 0; i < playerNum; i++) {
			try {
				players.add(i, new Player(names[i]));
			} catch (Exception e) {
				i--;
				continue;
			}
		}
	}

	public void inputPlayerNumber(int number) {
		this.playerNum = number;
	}

	public List<Player> getPlayers() {
		return players;
	}

}
