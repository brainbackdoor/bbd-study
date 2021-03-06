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

	/*
	 * 1~10 랜덤 Score 생성
	 */
	public void inputFrameScore(int pos) {
		frameScore.add(pos, randomScore());
	}

	private int randomScore() {
		return new Random().nextInt(10) + 1;
	}

	public void inputRestFrameScore(int pos, int point) {
		frameScore.add(pos, randomRestScore(point));
	}

	private int randomRestScore(int point) {
		return new Random().nextInt(10 - point) + 1;
	}

	/*
	 * 점수 계산
	 */
	public int getFrameScore(int pos) {
		return frameScore.get(pos);
	}

	public Turn calculate(Turn frame) {
		if (checkStrike(frame)) {
			return calculateStrike(frame);
		}
		if (checkSpare(getFrameScore(frame.getFirst()), getFrameScore(frame.getSecond()))) {
			return calculateSpare(frame);
		}
		frame.setFrameScore(getFrameScore(frame.getFirst()) + getFrameScore(frame.getSecond()));
		return frame;
	}

	public boolean checkStrike(Turn frame) {
		int point = getFrameScore(frame.getFirst());
		if (point == 10)
			return true;
		return false;
	}

	private Turn calculateStrike(Turn turn) {
		Turn nextTurn = generateNextTurn(turn.getFirst());
		int firstSumScore = getFrameScore(nextTurn.getFirst());
		int secondSumScore = getFrameScore(nextTurn.getSecond());
		turn.setFrameScore(10 + firstSumScore + secondSumScore);
		turn.onStrike();
		return turn;
	}

	private Turn generateNextTurn(int turn) {
		return new Turn((turn/2 + 1) * 2);
	}

	public boolean checkSpare(int pointFirst, int pointSecond) {
		if (pointFirst + pointSecond == 10) {
			return true;
		}
		return false;
	}

	private Turn calculateSpare(Turn turn) {
		Turn nextTurn = generateNextTurn(turn.getFirst());
		int firstSumScore = getFrameScore(nextTurn.getFirst());
		turn.setFrameScore(10 + firstSumScore);
		return turn;
	}
/*
 * 
 */
	public void oneFrame(Turn turn) {
		inputFrameScore(turn.getFirst());
	}

	public void oneRestFrame(Turn turn) {
		inputRestFrameScore(turn.getSecond(), getFrameScore(turn.getFirst()));
	}

	public boolean isStrike(Turn frame) {
		if (checkStrike(frame)) {
			return true;
		}
		return false;
	}
	
	public boolean isSpare(Turn frame) {
		if (checkSpare(getFrameScore(frame.getFirst()), getFrameScore(frame.getSecond()))) {
			return true;
		}
		return false;
	}
}
