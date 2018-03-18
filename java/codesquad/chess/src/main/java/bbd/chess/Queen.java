package bbd.chess;

public class Queen {
	public static boolean checkMovement(Piece piece, Position pos) {

		if (pos.boundaryMovement()) {
			return false;
		}
		if (!pos.crossMovement(piece) && !pos.diagonalMovement(piece)) {
			return false;
		}
		return true;
	}
}
