package bowling;

public class Turn {
	int first;
	int second;
	int isStrike = 0; // 0 : off | 1 : on
	int frameScore ;
	
	public Turn(int turn) {
		this.first = turn;
		this.second = turn + 1;
	}

	public int getFirst() {
		return first;
	}

	public int getSecond() {
		return second;
	}
	
	public void onStrike() {
		this.isStrike = 1;
	}
	public void offStrike() {
		this.isStrike = 0;
	}
	public int checkStrike() {
		return isStrike;
	}
	public void setFrameScore(int frameScore) {
		this.frameScore = frameScore;
	}

	public int getFrameScore() {
		return frameScore;
	}
	
	
}
