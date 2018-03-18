package bowling;

import java.util.ArrayList;
import java.util.List;

public class Result {
	private ArrayList<Integer> resultScore = new ArrayList<>();

	public void calculateResult(Score score, Player player) {
		Turn frame = score.calculate(new Turn(0));
		int sum = frame.getFrameScore();
		for (int i = 1; i <= player.getPlayerTurn().getFirst(); i++) {
			frame = score.calculate(new Turn(i * 2));
			sum += frame.getFrameScore();
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
