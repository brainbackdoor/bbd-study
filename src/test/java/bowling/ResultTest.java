package bowling;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ResultTest {
	
Game game = new Game();
List<Player> players = new ArrayList<>();
Result result = new Result();


	@Test
	public void 한명_결과_계산() {
		game.init();
		players = game.getPlayers();
		game.playGame(players.get(0));
		Score score = players.get(0).getScore();
		result.calculateResult(score);
		
		
	}




}
