package bbd.chess;

public class Piece {
	Color color;
	Type type;
	Position pos;
	char representation;

	public enum Color {
		WHITE, BLACK, NOCOLOR;
	}

	public enum Type {
		PAWN('p', 1.0), ROOK('r', 5.0), KNIGHT('n', 2.5), BISHOP('b', 3.0), QUEEN('q', 9.0), KING('k',
				0.0), NO_PIECE('.', 0.0);

		private char representation = '.';
		private double defaultPoint;

		private Type(char representation, double defaultPoint) {
			this.representation = representation;
			this.defaultPoint = defaultPoint;
		}

		public double getDefaultPoint() {
			return defaultPoint;
		}

		public char getWhiteRepresentation() {
			return this.representation;
		}

		public char getBlackRepresentation() {
			return Character.toUpperCase(this.representation);
		}
	}

	Piece(Color color, Type type) {
		this.color = color;
		this.type = type;
		this.representation = (color == Color.BLACK) ? type.getBlackRepresentation() : type.getWhiteRepresentation();
	}

	Piece(Color color, Type type, Position pos) {
		this.color = color;
		this.type = type;
		this.pos = pos;
		this.representation = (color == Color.BLACK) ? type.getBlackRepresentation() : type.getWhiteRepresentation();
	}

	public Position getPos() {
		return pos;
	}

	public Color getColor() {
		return color;
	}

	public Type getType() {
		return type;
	}

	public char getRepresentation() {
		return representation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		result = prime * result + representation;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Piece other = (Piece) obj;
		if (color != other.color)
			return false;
		if (pos == null) {
			if (other.pos != null)
				return false;
		} else if (!pos.equals(other.pos))
			return false;
		if (representation != other.representation)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Piece [color=" + color + ", type=" + type + ", pos=" + pos + ", representation=" + representation + "]";
	}

}
