package bowling;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GameTest {
	Game game = new Game();
	List<Player> players = new ArrayList<>();
	

	@Test
	public void 초기입력값_출력() {
		game.init();
		players = game.getPlayers();
		for (Player player : players) {
			assertEquals("PPP",player.getName());
		}
	}

}
