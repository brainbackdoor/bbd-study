package bowling;

import java.util.ArrayList;
import java.util.List;

public class Result {
	List<Integer> resultScore = new ArrayList<>();
	
	public void calculateResult(Score score) {
		for (int i = 0; i < 10; i++) {
			
			resultScore.add(score.calculate(i));

			System.out.println(score.getFrameScore(i));
		}
	}
	

}
