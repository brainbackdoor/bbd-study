package bowling;

import java.util.ArrayList;
import java.util.List;

public class Score {
	List<Integer> frameScore = new ArrayList<>();
	
	public void inputScore(int point) {
		frameScore.add(point);
	}
	
	public int getScore(int pos) {
		return frameScore.get(pos);
	}
}
