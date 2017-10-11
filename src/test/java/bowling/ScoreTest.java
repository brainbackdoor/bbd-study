package bowling;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScoreTest {
Score score = new Score();
	@Test
	public void inputTest() {

		score.inputFrameScore(0);
		System.out.println(score.getframeScore(0));
	}
//	@Test
//	public void 랜덤점수_테스트() {
//		System.out.println(score.randomScore());
//		
//	}
}
