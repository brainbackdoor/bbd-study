package task01;

class BinaryGap {

	boolean flag = false;
	public int solution(int N) {
		return calculateGap(N, 0, -1);
	}

	private int calculateGap(int N, int result, int length) {
		if (N == 0) return result;
		if (N % 2 == 1) {
			result = result > length ? result : length;
			flag = true;
			return calculateGap(N / 2, result, 0);
		}
		if( flag == true ) length ++;
		return calculateGap(N / 2, result, length);
	}
}