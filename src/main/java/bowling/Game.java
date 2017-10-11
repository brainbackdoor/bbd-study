package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	List<Player> players;
	int playerNum;

	public void init() {
		String playerName;
		players = new ArrayList<>();

		inputPlayerNumber();

		inputPlayersName();
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

}
