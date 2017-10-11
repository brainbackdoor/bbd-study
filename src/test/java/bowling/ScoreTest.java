package bowling;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScoreTest {
Score score = new Score();
	@Test
	public void inputTest() {
		int point = 5;
		score.inputScore(point);
		System.out.println(score.getScore(0));
	}

}
