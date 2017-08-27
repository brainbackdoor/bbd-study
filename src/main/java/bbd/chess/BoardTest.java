package bbd.chess;

import static org.junit.Assert.*;

import org.junit.Test;

import bbd.chess.Piece.Color;
import bbd.chess.Piece.Type;

public class BoardTest {

	@Test
	public void moveKing() throws Exception{
		Board board = new Board();
		board.initialze();
		board.setPositionOfPiece(new Piece(Color.WHITE, Type.KING, new Position("c3")));
		board.setPositionOfPiece("c3", "d4");
		Show.print(board);
	}
	
	@Test
	public void move() throws Exception{
		Board board = new Board();
		board.initialze();
		board.setPositionOfPiece(new Piece(Color.BLACK, Type.KING, new Position("a1")));
		board.setPositionOfPiece("a1", "b3");
		Show.print(board);
	}
	
	@Test
	public void point() throws Exception{
		Board board = new Board();
		board.initialze();

		Piece piece = new Piece(Color.BLACK, Type.PAWN, new Position("a1"));
		Piece piece2 = new Piece(Color.BLACK, Type.PAWN, new Position("h7"));
		board.setPositionOfPiece(new Piece(Color.BLACK, Type.PAWN, new Position("b6")));
		board.setPositionOfPiece(new Piece(Color.BLACK, Type.QUEEN, new Position("e6")));
		board.setPositionOfPiece(new Piece(Color.BLACK, Type.KING, new Position("b8")));
		board.setPositionOfPiece(new Piece(Color.BLACK, Type.ROOK, new Position("c8")));
		
		board.setPositionOfPiece(new Piece(Color.WHITE, Type.PAWN, new Position("f2")));
		board.setPositionOfPiece(new Piece(Color.WHITE, Type.PAWN, new Position("g2")));
		board.setPositionOfPiece(new Piece(Color.WHITE, Type.ROOK, new Position("e1")));
		board.setPositionOfPiece(new Piece(Color.WHITE, Type.KING, new Position("f1")));	
        assertEquals(15.0, board.caculcatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, board.caculcatePoint(Color.WHITE), 0.01);
		Show.print(board);
	}
	@Test
	public void print() throws Exception{
		Board board = new Board();
		board.initialze();
		Piece piece = new Piece(Color.BLACK, Type.PAWN, new Position("a8"));
		board.setPositionOfPiece(piece, "b5");
		assertEquals(piece, board.findPiece("b5"));
		Show.print(board);
		
		
	}

}
