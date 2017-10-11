package bowling;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BoardTest {
	Board board = new Board();

	Game game = new Game();
	List<Player> players = new ArrayList<>();
	Result result = new Result();

	@Test
	public void 여러명_결과_계산_출력() {
		game.init();
		players = game.getPlayers();

		Score score = players.get(0).getScore();
		for (int i = 0; i < 10; i++) {
			for (Player player : players) {

				game.playGame(player, new Turn(i * 2));
				
			}
		}
//		for (Player player : players) {
//			game.playGameFinalize(player);
//		}
	}

	@Test
	public void 초기출력_테스트() {
		game.init();

		// board.showBoard(game);
	}

}
