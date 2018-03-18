package bbd.chess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bbd.chess.Piece.Color;
import bbd.chess.Piece.State;
import bbd.chess.Piece.Type;

public class Board {
	List<Piece> pieces = new ArrayList<>();

	Color turn;

	Board() {
		this.turn = Color.BLACK;
	}

	public void changeTurn() {
		if (turn.equals(Piece.Color.BLACK))
			this.turn = Color.WHITE;
		else
			this.turn = Color.BLACK;
	}

	public boolean checkTurn(Piece piece) {
		if (turn.equals(piece.getColor())) {
			return true;
		}
		return false;
	}

	public void setPositionOfPiece(Piece piece) {
		pieces.set(piece.getPos().arrayNumPosition(), piece);
	}

	public boolean setPositionOfPiece(Piece piece, String nextPosition) {
		if (!checkTurn(piece)) {
			System.out.println(piece.getColor() + "턴이 아닙니다.");
			return false;
		}
		pieces.set(piece.getPos().arrayNumPosition(), new Piece(Color.NOCOLOR, Type.NO_PIECE));
		pieces.set(new Position(nextPosition).arrayNumPosition(), new Piece(piece, new Position(nextPosition)));
		return true;
	}

	public boolean setPositionOfPiece(String priPosition, String nextPosition) {
		if (!checkTurn(findPiece(priPosition))) {
			System.out.println(findPiece(priPosition).getColor() + "턴이 아닙니다.");
			return false;
		}
//		if (bePlaced(findPiece(priPosition), nextPosition)) {
//			return false;
//		}
		if (Position.checkMovement(findPiece(priPosition), nextPosition)) {
			pieces.set(new Position(nextPosition).arrayNumPosition(),
					new Piece(findPiece(priPosition), new Position(nextPosition), State.LIVE));
			pieces.set(new Position(priPosition).arrayNumPosition(), new Piece(Color.NOCOLOR, Type.NO_PIECE));
		}
		return true;
	}

	public Piece findPiece(String position) {
		Position pos = new Position(position);
		return pieces.get(pos.arrayNumPosition());
	}
	public boolean findPlaced(Position pos) {
		if(findPiece(pos.getPos()).getType() != Type.NO_PIECE) return true;
		return false;
	}
	public boolean findPlacedOneCross(Piece piece, int distance, String vector) {
		Position up  = new Position(Character.getNumericValue(piece.getPos().getxPos())+Integer.toString(piece.getPos().getyPos()-distance)); 
		Position down  = new Position(Character.getNumericValue(piece.getPos().getxPos())+Integer.toString(piece.getPos().getyPos()+distance)); 
		Position left  = new Position(Character.getNumericValue(piece.getPos().getxPos()-distance)+Integer.toString(piece.getPos().getyPos())); 
		Position right  = new Position(Character.getNumericValue(piece.getPos().getxPos()+distance)+Integer.toString(piece.getPos().getyPos()));
		System.out.println("piece : "+piece+" distance : "+distance+" vector : "+vector);
		switch(vector){
			case "up" : if(findPlaced(up))return true;break;
			case "down" : if(findPlaced(down))return true;break;
			case "left" : if(findPlaced(left))return true;break;
			case "right" : if(findPlaced(right))return true;break;
		}
		return false;
	}
	public boolean findPlacedCross(Piece piece, int distance, String vector) {
		for (int i = 1; i < distance; i++) {
			if(findPlacedOneCross(piece,i,vector))return true;
		}
		return false;
	}
	public boolean bePlacedCross(Piece piece, Position pos) {
		int diffXPos = Math.abs(piece.getxPos() - pos.getxPos());
		int diffYPos = Math.abs(piece.getyPos() - pos.getyPos());
		
		if((piece.getxPos() == pos.getxPos()) && (piece.getyPos()>pos.getyPos())) {
			return findPlacedCross(piece,diffYPos, "down");
		}
		if((piece.getxPos() == pos.getxPos()) && (piece.getyPos()<pos.getyPos())) {
			return findPlacedCross(piece,diffYPos, "up");
		}
		if((piece.getyPos() == pos.getyPos()) && (piece.getxPos()>pos.getxPos())) {
			return findPlacedCross(piece,diffXPos, "left");
		}
		if((piece.getyPos() == pos.getyPos()) && (piece.getxPos()<pos.getxPos())) {
			return findPlacedCross(piece,diffXPos, "right");
		}
		return false;
	}
	public boolean bePlaced(Piece piece, String position) {
		Position pos = new Position(position);
		switch (Character.toUpperCase(piece.getRepresentation())) {
		// case 'K':if (King.checkMovement(piece, pos))return true;
//		case 'Q':if ((bePlacedCross(piece, pos))||(bePlacedDiagonal(piece, pos)))return true;break;
		case 'R':if (bePlacedCross(piece, pos))	return true;break;
//		case 'B':if (bePlacedDiagonal(piece, pos))return true;break;
			// case 'N':if (Knight.checkMovement(piece, pos))return true;break;
			// case 'P':if (Pawn.checkMovement(piece, pos))return true;break;
		}
		return false;
	}



	public double caculcatePoint(Color color) {
		double count = 0;
		for (int i = 0; i < 64; i++) {
			if (pieces.get(i).getColor().equals(color)) {
				// System.out.println(color + ":" + pieces.get(i).getType().getDefaultPoint());
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

	public Color getTurn() {
		return turn;
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
