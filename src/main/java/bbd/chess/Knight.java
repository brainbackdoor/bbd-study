package bbd.chess;

public class Knight{
	public static boolean checkMovement(Piece piece, Position pos) {

		if (pos.boundaryMovement()) {
			return false;
		}
		if (!pos.knightMovement(piece)) {
			return false;
		}
		return true;
	}

}
