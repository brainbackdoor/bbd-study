package bbd.chess;

public class Rook {
	public static boolean checkMovement(Piece piece, Position pos) {

		if (pos.boundaryMovement()) {
			return false;
		}
		if (!pos.crossMovement(piece)) {
			return false;
		}
		return true;
	}
}
