package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Score {
	List<Integer> frameScore = new ArrayList<>();
	Score(){
		for (int i = 0; i < 20; i++) {
			frameScore.add(i, 0);
		}
	}
	public void inputFrameScore(int pos) {
		frameScore.add(pos,randomScore());
	}
	public void inputRestFrameScore(int pos,int point) {
		frameScore.add(pos,randomRestScore(point));
	}	
	public int getframeScore(int pos) {
		return frameScore.get(pos);
	}
	
	private int randomScore() {
		return new Random().nextInt(10) + 1;
	}
	private int randomRestScore(int point) {
		return new Random().nextInt(10-point) + 1;
	}
}
