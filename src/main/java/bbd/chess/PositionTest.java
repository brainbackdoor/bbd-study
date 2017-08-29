package bbd.chess;

import static org.junit.Assert.*;

import org.junit.Test;

import bbd.chess.Piece.Color;
import bbd.chess.Piece.Type;

public class PositionTest {

	@Test
	public void pawnMovement() {
		Board board = new Board();
		board.initialze();
		board.setPositionOfPiece(new Piece(Color.WHITE, Type.PAWN, new Position("c7")));
		board.setPositionOfPiece("c7", "c5");
		board.setPositionOfPiece("c5", "c4");
		board.setPositionOfPiece("c4", "c2");
		Show.print(board);
	}
	
	@Test
	public void knightMovement() {
		Board board = new Board();
		board.initialze();
		board.setPositionOfPiece(new Piece(Color.WHITE, Type.KNIGHT, new Position("c3")));
//		board.setPositionOfPiece("c3", "d1");
//		board.setPositionOfPiece("c3", "b1");
//		board.setPositionOfPiece("c3", "a2");
//		board.setPositionOfPiece("c3", "a4");
//		board.setPositionOfPiece("c3", "b5");
//		board.setPositionOfPiece("c3", "d5");
//		board.setPositionOfPiece("c3", "e2");
//		board.setPositionOfPiece("c3", "e4");

		Show.print(board);
	}

	@Test
	public void kingMovement() {
		Board board = new Board();
		board.initialze();
		board.setPositionOfPiece(new Piece(Color.WHITE, Type.QUEEN, new Position("c3")));
		board.setPositionOfPiece("c3", "d2");

		Show.print(board);
	}

	@Test
	public void initialize() {
		Position pos = new Position("a8");
		assertEquals(0, pos.xPos);
		assertEquals(7, pos.yPos);

	}

}
