package bowling;

public class Turn {
	int first;
	int second;

	Turn(int turn) {
		this.first = turn;
		this.second = turn + 1;
	}

	public int getFirst() {
		return first;
	}

	public int getSecond() {
		return second;
	}

}
