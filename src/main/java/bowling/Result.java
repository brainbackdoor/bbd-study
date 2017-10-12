package bowling;

import java.util.ArrayList;
import java.util.List;

public class Result {
	ArrayList<Integer> resultScore = new ArrayList<>();
	
	public void calculateResult(Score score) {
		int sum = score.calculate(new Turn(0));
		for (int frame = 1; frame <= resultScore.size(); frame++) {

			sum += score.calculate(new Turn(frame * 2));
		}
		appendResultScore(score, sum);
	}

	private void appendResultScore(Score score, int sum) {
			resultScore.add(sum);
	}

	public int getResultScoreFrame(int frame) {
		if (frame >= 0) {
			return resultScore.get(frame);
		}
		return 0;
	}

	public int getResultScoreSpareFrame(int frame) {
		return resultScore.get(frame + 1);
	}

	public int playTurn() {
		return resultScore.size();
	}
}
