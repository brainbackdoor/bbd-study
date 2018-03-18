package bbd.chess;

public class King {

	public static boolean checkMovement(Piece piece, Position pos) {
		int nextXPosCheck = Math.abs(pos.xPos - piece.getPos().xPos);
		int nextYPosCheck = Math.abs(pos.yPos - piece.getPos().yPos);

		if (pos.boundaryMovement()) {
			return false;
		}
		if (nextXPosCheck > 1 || nextYPosCheck > 1) {
			return false;
		}
		return true;
	}
}
