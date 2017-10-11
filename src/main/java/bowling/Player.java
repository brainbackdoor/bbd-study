package bowling;

public class Player {
	String name;
	Score score;
	Result result;

	Player(String name) throws Exception {
		if (!confirmPlayerName(name)) {
			throw new Exception();
		}
		this.name = name;
		this.score = new Score();
	}

	public void rollingBall(int turn) {
		score.inputFrameScore(turn*2);
		if(score.getFrameScore(turn*2)!=10) {
			score.inputRestFrameScore(turn*2+1,score.getFrameScore(turn*2));
		}
	}

	public boolean confirmPlayerName(String playerName) {
		if (playerName.length() != 3) {
			return false;
		}
		return true;
	}

	public String getName() {
		return name;
	}

	public Score getScore() {
		return score;
	}

}
