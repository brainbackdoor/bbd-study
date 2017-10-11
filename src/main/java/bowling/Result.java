package bowling;

import java.util.ArrayList;
import java.util.List;

public class Result {
	List<Integer> resultScore = new ArrayList<>();

	
	
	public void calculateResult(Score score) {
		for (int turn = 0; turn < 10; turn++) {
			resultScore.add(getResultScoreTurn(turn - 1) + score.calculate(turn));
		}
	}

	public int getResultScoreTurn(int turn) {
		if (turn >= 0) {
			return resultScore.get(turn);
		}
		return 0;
	}

	

}
