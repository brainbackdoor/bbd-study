package bowling;

import java.util.ArrayList;
import java.util.List;

public class Result {
	List<Integer> resultScore = new ArrayList<>();
	
	public void calculateResult(Score score) {
		for (int i = 0; i < 10; i++) {
			
			resultScore.add(getResultScorePriTurn(i-1)+score.calculate(i));

			System.out.println(resultScore.get(i));
		}
	}
	
	private int getResultScorePriTurn(int pos) {
		if(pos >= 0) {
			return resultScore.get(pos);
		}
		return 0;
	}
	

}
