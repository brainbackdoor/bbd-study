package bbd.chess;

import java.util.ArrayList;
import java.util.List;

import bbd.chess.Piece.Color;
import bbd.chess.Piece.Type;

public class Board {
	List<Piece> pieces = new ArrayList<>();

	public void setPositionOfPiece(Piece piece) {
		pieces.set(piece.getPos().arrayNumPosition(), piece);
	}

	public void setPositionOfPiece(Piece piece, String nextPosition) {

		pieces.set(new Position(nextPosition).arrayNumPosition(), piece);
		pieces.set(piece.getPos().arrayNumPosition(), new Piece(Color.NOCOLOR, Type.NO_PIECE));

	}

	public void setPositionOfPiece(String priPosition, String nextPosition) {
		if (Position.checkMovement(findPiece(priPosition), nextPosition)) {
			pieces.set(new Position(nextPosition).arrayNumPosition(), findPiece(priPosition));
			pieces.set(new Position(priPosition).arrayNumPosition(), new Piece(Color.NOCOLOR, Type.NO_PIECE));
		}
	}

	public Piece findPiece(String position) {
		Position pos = new Position(position);
		return pieces.get(pos.arrayNumPosition());
	}

	public double caculcatePoint(Color color) {
		double count = 0;
		for (int i = 0; i < 64; i++) {
			if (pieces.get(i).getColor().equals(color)) {
				System.out.println(color + ":" + pieces.get(i).getType().getDefaultPoint());
				count += pieces.get(i).getType().getDefaultPoint();
			}
		}
		return count;
	}

	/**
	 * Initialize
	 */
	public void initialze() {
		for (int i = 1; i <= 8; i++) {
			// initializeLine(i);
			initializedefault(i);
		}
	}

	public void initializedefault(int line) {
		for (int i = 0; i < 8; i++) {
			pieces.add(
					new Piece(Color.NOCOLOR, Type.NO_PIECE, new Position(Character.toString((char) (97 + i)) + line)));
		}
	}

	public void initializeLine(int line) {
		for (int i = 0; i < 8; i++) {
			pieces.add(
					new Piece(Color.NOCOLOR, Type.NO_PIECE, new Position(Character.toString((char) (97 + i)) + line)));
		}
		if (line == 1) {
			setPositionOfPiece(new Piece(Color.BLACK, Type.ROOK, new Position("a1")));
			setPositionOfPiece(new Piece(Color.BLACK, Type.KNIGHT, new Position("b1")));
			setPositionOfPiece(new Piece(Color.BLACK, Type.BISHOP, new Position("c1")));
			setPositionOfPiece(new Piece(Color.BLACK, Type.QUEEN, new Position("d1")));
			setPositionOfPiece(new Piece(Color.BLACK, Type.KING, new Position("e1")));
			setPositionOfPiece(new Piece(Color.BLACK, Type.BISHOP, new Position("f1")));
			setPositionOfPiece(new Piece(Color.BLACK, Type.KNIGHT, new Position("g1")));
			setPositionOfPiece(new Piece(Color.BLACK, Type.ROOK, new Position("h1")));
		}
		if (line == 8) {
			setPositionOfPiece(new Piece(Color.WHITE, Type.ROOK, new Position("a8")));
			setPositionOfPiece(new Piece(Color.WHITE, Type.KNIGHT, new Position("b8")));
			setPositionOfPiece(new Piece(Color.WHITE, Type.BISHOP, new Position("c8")));
			setPositionOfPiece(new Piece(Color.WHITE, Type.QUEEN, new Position("d8")));
			setPositionOfPiece(new Piece(Color.WHITE, Type.KING, new Position("e8")));
			setPositionOfPiece(new Piece(Color.WHITE, Type.BISHOP, new Position("f8")));
			setPositionOfPiece(new Piece(Color.WHITE, Type.KNIGHT, new Position("g8")));
			setPositionOfPiece(new Piece(Color.WHITE, Type.ROOK, new Position("h8")));
		}
		if (line == 2) {
			for (int i = 0; i < 8; i++) {
				setPositionOfPiece(
						new Piece(Color.BLACK, Type.PAWN, new Position(Character.toString((char) (97 + i)) + line)));
			}
		}
		if (line == 7) {
			for (int i = 0; i < 8; i++) {
				setPositionOfPiece(
						new Piece(Color.WHITE, Type.PAWN, new Position(Character.toString((char) (97 + i)) + line)));
			}
		}

	}

	public List<Piece> getPieces() {
		return pieces;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pieces == null) ? 0 : pieces.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (pieces == null) {
			if (other.pieces != null)
				return false;
		} else if (!pieces.equals(other.pieces))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Board [pieces=" + pieces + "]";
	}

}
