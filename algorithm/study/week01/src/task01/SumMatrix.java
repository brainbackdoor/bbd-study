package task01;

public class SumMatrix {
	int[][] sumMatrix(int[][] A, int[][] B) {
		int[][] answer = A;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				answer[i][j] = A[i][j] + B[i][j];
			}
		}
		return answer;
	}

	// 아래는 테스트로 출력해 보기 위한 코드입니다.
	public static void main(String[] args) {
		SumMatrix c = new SumMatrix();
		int[][] A = { { 5, 5 }, { 5, 9 } };
		int[][] B = { { 7, 6 }, { 3, 3 } };
		int[][] answer = c.sumMatrix(A, B);
		if (answer[0][0] == 12 && answer[0][1] == 11 && answer[1][0] == 8 && answer[1][1] == 12) {
			System.out.println("맞았습니다. 제출을 눌러 보세요");
		} else {
			System.out.println("틀렸습니다. 수정하는게 좋겠어요");
		}
	}
}