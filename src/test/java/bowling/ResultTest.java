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
		for (int i = 0; i < 10; i++) {
			System.out.println(players.get(0).getScore().getFrameScore(i*2)
					+" + "
					+ players.get(0).getScore().getFrameScore(i*2+1)
					+" = "
					+ score.calculate(i));
		}
		result.calculateResult(score);
		
	}




}
