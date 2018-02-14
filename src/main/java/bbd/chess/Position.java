package bbd.chess;

public class Position {
	private static int row = 8;
	int xPos;
	int yPos;
	String pos ;
	int oneSquareRight = xPos + 1;
	int oneSquareLeft = xPos - 1;
	int oneSqaureUp = yPos - 1;
	int oneSquareDown = yPos + 1;

	Position() {
	}

	Position(String pos) {
		char x = pos.charAt(0);
		this.xPos = x - 'a';

		char y = pos.charAt(1);
		this.yPos = Character.getNumericValue(y) - 1;
		this.pos = pos;
	}
	

	public int arrayNumPosition() {
		return xPos + yPos * row;
	}

	public static boolean checkMovement(Piece piece, String position) {
		Position pos = new Position(position);
		switch (Character.toUpperCase(piece.getRepresentation())) {
		case 'K':if (King.checkMovement(piece, pos))return true;break;
		case 'Q':if (Queen.checkMovement(piece, pos))return true;break;
		case 'R':if (Rook.checkMovement(piece, pos))return true;break;
		case 'B':if (Bishop.checkMovement(piece, pos))return true;break;
		case 'N':if (Knight.checkMovement(piece, pos))return true;break;
		case 'P':if (Pawn.checkMovement(piece, pos))return true;break;
		}
		return false;
	}

	public boolean boundaryMovement() {
		if ((oneSquareRight > 7) || (oneSquareLeft < -1) || (oneSquareDown > 7) || (oneSqaureUp < -1)) {
			return true;
		}
		return false;
	}

	public boolean crossMovement(Piece piece) {
		int xPosLineCheck = piece.getxPos();
		int yPosLineCheck = piece.getyPos();
		if ((xPos != xPosLineCheck) && (yPos != yPosLineCheck)) {
			return false;
		}
		return true;
	}

	public boolean diagonalMovement(Piece piece) {
		int xPosCheck = piece.getxPos();
		int yPosCheck = piece.getyPos();
		if (Math.abs(xPos - xPosCheck) != Math.abs(yPos - yPosCheck)) {
			return false;
		}
		return true;
	}

	public boolean knightMovement(Piece piece) {
		int xPosPlusOne = piece.getxPos() + 1;
		int xPosMinusOne = piece.getxPos() - 1;
		int xPosPlusTwo = piece.getxPos() + 2;
		int xPosMinusTwo = piece.getxPos() - 2;
		int yPosPlusOne = piece.getyPos() + 1;
		int yPosMinusOne = piece.getyPos() - 1;
		int yPosPlusTwo = piece.getyPos() + 2;
		int yPosMinusTwo = piece.getyPos() - 2;

		if ((xPos == xPosPlusOne || xPos == xPosMinusOne) && (yPos == yPosPlusTwo || yPos == yPosMinusTwo)) {
			return true;
		}
		if ((yPos == yPosPlusOne || yPos == yPosMinusOne) && (xPos == xPosPlusTwo || xPos == xPosMinusTwo)) {
			return true;
		}
		return false;
	}

	public boolean pawnMovement(Piece piece) {
		int yPosPlusOne = piece.getyPos() + 1;
		int yPosMinusOne = piece.getyPos() - 1;
		int yPosCheck = yPos ;

		if (piece.getState().equals(Piece.State.INITIAL)) {
			return pawnInitialMovement(piece);
		}

		if (xPos != piece.getxPos()) {
			return false;
		}
		if (piece.getColor().equals(Piece.Color.WHITE) && (yPosCheck == yPosMinusOne)) {
			return true;
		}
		if (piece.getColor().equals(Piece.Color.BLACK) && (yPosCheck == yPosPlusOne)) {
			return true;
		}
		return false;
	}

	public boolean pawnInitialMovement(Piece piece) {
		int yPosPlusTwo = piece.getyPos() + 2;
		int yPosMinusTwo = piece.getyPos() - 2;
		int yPosPlusOne = piece.getyPos() + 1;
		int yPosMinusOne = piece.getyPos() - 1;
		int yPosCheck = yPos ;
		if (xPos != piece.getxPos()) {
			return false;
		}
		if (piece.getColor().equals(Piece.Color.WHITE) && (yPosCheck == yPosMinusTwo)) {
			return true;
		}
		if (piece.getColor().equals(Piece.Color.BLACK) && (yPosCheck == yPosPlusTwo)) {
			return true;
		}
		if (piece.getColor().equals(Piece.Color.WHITE) && (yPosCheck == yPosMinusOne)) {
			return true;
		}
		if (piece.getColor().equals(Piece.Color.BLACK) && (yPosCheck == yPosPlusOne)) {
			return true;
		}
		return false;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public String getPos() {
		return pos;
	}
	
	
}
