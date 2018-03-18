package bowling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
	
	String title = "";
	static String field = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n";
	String point = "";
	String result = "";

	public Map<String,String> showBoard(Player player) {
		Map<String,String> returnString = new HashMap();
		title += player.getName() + "'s turn : " + player.lastScore + "\n";

		showPoint(player);
		showResult(player);

		returnString.put("title", title);
		returnString.put("field", field);
		returnString.put("point", point);
		returnString.put("result", result);
		return returnString;
	}

	/*
	 * Player's Bowling Result
	 */
	private void showResult(Player player) {
		result += "|      |";
		for (int frame = 0; frame < player.getTurn(); frame++) {
			result += "  " + convertNumber(player.getResultScoreFrame(frame)) + "  |";
		}
		for (int frame = 0; frame < 10 - player.getTurn(); frame++) {
			result += "      |";
		}
		result += "\n";
	}

	private String convertNumber(int number) {
		if (number < 10) {
			return "0" + Integer.toString(number);
		}
		return Integer.toString(number);
	}

	/*
	 * Player's Bowling Game Point
	 */
	private void showPoint(Player player) {
		Score score = player.getScore();
		showPlayerName(player);
		for (int i = 0; i < 10; i++) {
			showPlayerScore(score, new Turn(i * 2));
		}
		point += "\n";
	}

	private void showPlayerScore(Score score, Turn turn) {
		point += "  " + convertFrameScoreFirst(score.getFrameScore(turn.getFirst()));
		if (!score.isStrike(turn)) {
			point += convertFrameScoreSecond(score.getFrameScore(turn.getSecond()),
					score.getFrameScore(turn.getFirst()));
		}
	}
	/*
	 * Player's name
	 */
	private void showPlayerName(Player player) {
		point += "|  " + player.getName() + " |";
	}
	/*
	 * convert for show
	 */
	private String convertFrameScoreFirst(int frameScore) {
		if (frameScore == 10) {
			return "X   |";
		}
		return Integer.toString(frameScore);
	}

	private String convertFrameScoreSecond(int frameScore, int priScore) {
		if (frameScore + priScore == 10) {
			return "|/ |";
		}
		return "|" + Integer.toString(frameScore) + " |";
	}
}
