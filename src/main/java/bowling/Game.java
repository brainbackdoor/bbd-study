package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
	List<Player> players;
	int playerNum;
	Board board;

	public void init() {
		String playerName;
		players = new ArrayList<>();
		board = new Board();
		inputPlayerNumber();
		inputPlayersName();
	}

	public void playGame(Player player, Turn frame) {
		Turn priFrame = new Turn(frame.getFirst() / 2 - 1);
		player.rollingBall(frame);

		if (player.isOneMoreRollingBall(frame)) {
			board.showBoard(player);
			player.rollingRestBall(frame);
		}
		player.calculateResultScore();
		board.showBoard(player);
	}

	public void playGameFinalize(Player player) {
		player.calculateResultScore();
		board.showBoard(player);
	}

	private void inputPlayersName() {
		Scanner scannerName = new Scanner(System.in);

		for (int i = 0; i < playerNum; i++) {
			System.out.printf("플레이어 %d의 이름은?(3 english letters):", i + 1);
			try {
				players.add(i, new Player(scannerName.nextLine()));
			} catch (Exception e) {
				i--;
				continue;
			}
		}
	}

	private void inputPlayerNumber() {
		Scanner scannerP = new Scanner(System.in);
		System.out.println("How many people?");
		playerNum = scannerP.nextInt();
	}

	public List<Player> getPlayers() {
		return players;
	}

	public int getPlayerNum() {
		return playerNum;
	}

}
