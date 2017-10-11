package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Score {
	List<Integer> frameScore = new ArrayList<>();

	Score() {
		for (int i = 0; i < 20; i++) {
			frameScore.add(i, 0);
		}
	}

	public void inputFrameScore(int pos) {
//		frameScore.add(pos, randomScore());
		frameScore.add(pos, 10);
	}

	public void inputRestFrameScore(int pos, int point) {
		frameScore.add(pos, randomRestScore(point));
	}

	public int getframeScore(int pos) {
		return frameScore.get(pos);
	}

	private int randomScore() {
		return new Random().nextInt(10) + 1;
	}

	private int randomRestScore(int point) {
		return new Random().nextInt(10 - point) + 1;
	}

	public int calculate(int pos) {
		if(checkStrike(getframeScore(pos*2))) {
			return calculateStrike(pos);
		}
		return 0;
	}

	private int calculateStrike(int pos) {
		int firstSumScore = getframeScore((pos + 1) * 2);
		int secondSumScore = extractSecondSumScore(pos, firstSumScore);
		return getframeScore(pos * 2) + firstSumScore + secondSumScore;
	}

	private int extractSecondSumScore(int pos, int firstSumScore) {
		if (checkStrike(firstSumScore)) {
			return getframeScore((pos + 2) * 2);
		}
		return getframeScore((pos + 1) * 2 + 1);
	}

	private boolean checkStrike(int point) {
		if (point == 10) {
			return true;
		}
		return false;
	}
}
