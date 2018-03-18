package task01;

import java.util.Arrays;

public class TryHelloWorld {
	public int[] gcdlcm(int a, int b) {
		int[] answer = new int[2];

		answer[0] = gcd(a, b);
		answer[1] = (a * b) / answer[0];
		return answer;

	}

	private int gcd(int a, int b) {
		int c = a > b ? b: a;

		while ((a % c != 0) || (b % c != 0)) c--;
		return c;
	}

	// 아래는 테스트로 출력해 보기 위한 코드입니다.
	public static void main(String[] args) {
		TryHelloWorld c = new TryHelloWorld();
		System.out.println(Arrays.toString(c.gcdlcm(3, 12)));
	}
}
