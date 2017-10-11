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
	
	public int getframeScore(int pos) {
		return frameScore.get(pos);
	}
	
	private int randomScore() {
		return new Random().nextInt(10) + 1;
	}

}
