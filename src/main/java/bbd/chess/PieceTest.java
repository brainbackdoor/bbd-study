package bbd.chess;

import static org.junit.Assert.*;

import org.junit.Test;

import bbd.chess.Piece.Color;
import bbd.chess.Piece.Type;

public class PieceTest {

	@Test
	public void initialize() {
		Piece piece = new Piece(Color.BLACK, Type.PAWN, new Position("a8"));
		assertEquals(Color.BLACK, piece.color);
		assertEquals(Type.PAWN, piece.type);
		assertEquals(0,piece.pos.xPos);
		assertEquals(7,piece.pos.yPos);
	}

}
