package bbd.chess;

public class Pawn {
	public static boolean checkMovement(Piece piece, Position pos) {

		if (pos.boundaryMovement()) {
			return false;
		}
		if (!pos.pawnMovement(piece)) {
			return false;
		}
		return true;
	}
}
