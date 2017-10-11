package bowling;

import java.util.List;

public class Board {
	static String field = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

	public void showBoard(Player player) {
		System.out.println(player.getName() + "'s turn : " + player.lastScore + "\n");
		System.out.println(field + "\n");

		showPoint(player);
		showResult(player);
	}
/*
 * Player's Bowling Result
 */
	private void showResult(Player player) {
		System.out.print("|      |");
		for (int frame = 0; frame < player.getTurn(); frame++) {
			System.out.print("  "+ convertNumber(player.getResultScoreFrame(frame))+"  |");
		}
		for (int frame = 0; frame < 10 -player.getTurn(); frame++) {
			System.out.print("      |");
		}
		System.out.println();
	}
	
	private String convertNumber(int number) {
		if(number<10) {
			return "0"+Integer.toString(number);
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
		System.out.println();
	}

	private void showPlayerScore(Score score, Turn turn) {
		System.out.print("  " + showFrameScoreFirst(score.getFrameScore(turn.getFirst())));
		if(!score.isStrike(turn)) {
			System.out.print(showFrameScoreSecond(score.getFrameScore(turn.getSecond()), score.getFrameScore(turn.getFirst())));
		}
	}

	private void showPlayerName(Player player) {
		System.out.print("|  " + player.getName() + " |");
	}

	private String showFrameScoreFirst(int frameScore) {
		if (frameScore == 10) {
			return "X   |";
		}
		return Integer.toString(frameScore);
	}

	private String showFrameScoreSecond(int frameScore, int priScore) {
		if (frameScore + priScore == 10) {
			return "|/ |";
		}
		return "|" + Integer.toString(frameScore)+" |";
	}


	public void showTurn(Player player) {

	}
}
