package bbd.chess;

import bbd.chess.Piece.Type;

public class Position {
	int xPos;
	int yPos;
	
	Position(String pos){
		char x = pos.charAt(0);
		this.xPos = x - 'a';
		
		char y = pos.charAt(1);
		this.yPos = Character.getNumericValue(y)-1;
	}

	public int arrayNumPosition() {
		return xPos + yPos*8; 
	}

	public static boolean checkMovement(Piece piece, String position) {
		Position pos = new Position(position);
		switch(piece.getRepresentation()) {
		
		case 'k':if(checkKingMovement(piece, pos))return true;break;
		case 'q':if(checkQueenMovement(piece, pos))return true;break;
		}
		return false;
	}

	private static boolean checkKingMovement(Piece piece, Position pos) {
		if((pos.xPos + 1 > 7) && (pos.xPos - 1 < -1) 
				&& (pos.yPos + 1 > 7) && (pos.yPos - 1 < -1)){
			return false;
		}
		if((Math.abs(pos.xPos - piece.getPos().xPos)>1 
				|| Math.abs(pos.yPos - piece.getPos().yPos)> 1) 
				|| (Math.abs(pos.xPos - piece.getPos().xPos) ==1 && Math.abs(pos.yPos - piece.getPos().yPos) ==1 )){
			return false;
		}
		return true;
	}
	private static boolean checkQueenMovement(Piece piece, Position pos) {
		System.out.println(pos.xPos);
		System.out.println(pos.yPos);
		System.out.println(piece.getPos().xPos);
		System.out.println(piece.getPos().yPos);
		
		if((pos.xPos + 1 > 7) || (pos.xPos - 1 < -1) 
				|| (pos.yPos + 1 > 7) || (pos.yPos - 1 < -1)){
			return false;
		}
		if((pos.xPos!= piece.getPos().xPos ) && (pos.yPos != piece.getPos().yPos)){
			return false;
		}
		return true;
	}


}
