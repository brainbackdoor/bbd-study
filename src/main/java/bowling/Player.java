package bowling;

public class Player {
	private String name;
	private Score score;
	private Result result;
	private Turn playerTurn;
	int lastScore = 0;

	Player(String name) throws Exception {
		if (!confirmPlayerName(name)) {
			throw new Exception();
		}
		if (!isLetter(name)) {
			throw new Exception();
		}
		this.name = name;
		this.score = new Score();
		this.result = new Result();
		this.playerTurn =  new Turn(0);
	}

	private boolean isLetter(String str) {
		for (int i = 0; i < str.length(); i++) {
			String chk = str.substring(i, i + 1);
			if (!chk.matches(".*[A-Z]*.")) {
				return false;
			}
		}
		return true;
	}

	public void rollingBall(Turn turn) {
		score.oneFrame(turn);
		lastScore = score.getFrameScore(turn.getFirst());
	}

	public void rollingRestBall(Turn turn) {
		score.oneRestFrame(turn);
		lastScore = score.getFrameScore(turn.getSecond());
	}

	public void calculateResultScore(Player player) {
		result.calculateResult(score, player);
	}

	public boolean confirmPlayerName(String playerName) {
		if (playerName.length() != 3) {
			return false;
		}
		return true;
	}

	public int getResultScoreFrame(int turn) {
		return result.getResultScoreFrame(turn);
	}

	public boolean isOneMoreRollingBall(Turn turn) {
		if (!score.isStrike(turn)) {
			return true;
		}
		return false;
	}

	public int getTurn() {
		return result.playTurn();
	}

	public String getName() {
		return name;
	}

	public Score getScore() {
		return score;
	}

	public Turn getPlayerTurn() {
		return playerTurn;
	}
	
	public void nextTurn() {
		this.playerTurn = new Turn(playerTurn.getFirst()+1);
	}

}
