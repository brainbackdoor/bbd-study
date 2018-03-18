package bbd.chess;

import java.util.Scanner;

import bbd.chess.Piece.Color;

public class Chess {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Board board = new Board();
		board.initialze();
		Show.print(board);
		while (true) {
			System.out.println("현재 턴 : " +board.getTurn());
			System.out.println("움직일 말");
			String piece = scanner.nextLine();
			System.out.println("움직일 위치");
			String position = scanner.nextLine();
			if(!board.setPositionOfPiece(piece, position)) {
				continue;
			}
			board.changeTurn();
			double blackPoint = board.caculcatePoint(Color.BLACK);
			double whitePoint = board.caculcatePoint(Color.WHITE);
			System.out.println("BLACK : " + blackPoint + "\nWHITE : " + whitePoint);
			Show.print(board);
			if(piece.equals("exit")) {
				break;
			}
		}
		scanner.close();
	}
}
