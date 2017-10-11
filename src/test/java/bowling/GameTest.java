package bowling;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GameTest {
	Game game = new Game();
	List<Player> players = new ArrayList<>();

	@Test
	public void 여러명_게임_테스트() {
		game.init();
		players = game.getPlayers();
		
		for (Player player : players) {
			game.playGame(player);
			for (int i = 0; i < 10; i++) {
				System.out.println(player.getScore().getFrameScore(i*2)
						+" + "
						+ player.getScore().getFrameScore(i*2+1)
						+" = "
						+ (player.getScore().getFrameScore(i*2)+player.getScore().getFrameScore(i*2+1)));
			}		
			System.out.println("");
		}

	}
	
	@Test
	public void 한명_게임_테스트() {
		game.init();
		players = game.getPlayers();
		game.playGame(players.get(0));
		for (int i = 0; i < 10; i++) {
			System.out.println(players.get(0).getScore().getFrameScore(i*2)
					+" + "
					+ players.get(0).getScore().getFrameScore(i*2+1)
					+" = "
					+ (players.get(0).getScore().getFrameScore(i*2)+players.get(0).getScore().getFrameScore(i*2+1)));
		}
	}

	@Test
	public void 초기입력값_출력() {
		game.init();
		players = game.getPlayers();
		for (Player player : players) {
			assertEquals("PPP",player.getName());
		}
	}

}
