package bbd.chess;

import java.util.List;

public class Show {
	public static void print(Board board) {
		for (int i = 0; i < 8; i++) {
			printLine(board.getPieces(),i);
			System.out.println();
		}
	}
	public static void printLine(List<Piece> pieces, int line) {
		for (int i = 0; i < 8; i++) {
			System.out.print(pieces.get(line*8 + i).getRepresentation()+"\t");
		}		
	}
}
