package bowling;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScoreTest {
Score score = new Score();

	@Test
	public void 스트라이크_테스트() {
		score.inputFrameScore(0);
		score.inputFrameScore(1);
		score.inputFrameScore(2);
		int temp = score.calculate(new Turn(2));
		System.out.println("결과 : "+temp);
	}

	@Test
	public void inputTest() {

//		score.inputFrameScore(0);
		System.out.println(score.getFrameScore(0));
	}
//	@Test
//	public void 랜덤점수_테스트() {
//		System.out.println(score.randomScore());
//		
//	}
}
