package bbd.chess;

public class Bishop {
	public static boolean checkMovement(Piece piece, Position pos) {

		if (pos.boundaryMovement()) {
			return false;
		}
		if (!pos.diagonalMovement(piece)) {
			return false;
		}
		return true;
	}
}
