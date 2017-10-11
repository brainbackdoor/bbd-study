package bowling;

import java.util.List;

public class Board {
	StringBuilder sb = new StringBuilder();

	public void showBoard(Game game) {
		sb.append("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n");
		List<Player> players = game.getPlayers();

		for (int i = 0; i < game.getPlayerNum(); i++) {
			appendPlayerScore(sb, players.get(i));
		}

		System.out.println(sb);

	}

	private StringBuilder appendPlayerScore(StringBuilder sb, Player player) {
		Score score = player.getScore();
		sb.append("|  ").append(player.getName());
		for (int i = 0; i < 10; i++) {

			sb.append(" |  ").append(confirmFrameScoreFirst(score.getFrameScore(i * 2)));
			sb.append(confirmFrameScoreSecond(score.getFrameScore(i * 2 + 1)));

		}
		sb.append(" |");

		return sb;
	}

	private String confirmFrameScoreFirst(int frameScore) {
		if (frameScore == 0) {
			return " ";
		}
		return Integer.toString(frameScore);
	}

	private String confirmFrameScoreSecond(int frameScore) {
		if (frameScore == 0) {
			return "  ";
		}
		return "|" + Integer.toString(frameScore);
	}
}
