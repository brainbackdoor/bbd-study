package bbd.domain;

import java.util.Scanner;

public class Game {
	public static int carCount ;
	public static int turn;
	public static void input() {
		Scanner scanner = new Scanner(System.in); 
		
		System.out.println("차 개수를 입력하세요");
		carCount = scanner.nextInt();
		System.out.println("횟수를 입력하세요");
		turn = scanner.nextInt();
		

	}
	public static void main(String[] args) {
		Scanner scannerName = new Scanner(System.in);
		
		input();
		Cars cars = new Cars();
		for (int i = 0; i < carCount; i++) {
			System.out.println((i+1)+"번째 차의 이름을 입력하세요");
			String name = scannerName.nextLine();
			cars.add(new Car(name));
		}
		System.out.println(Print.carsPrint(cars));
		for (int i = 0; i < turn; i++) {
			cars.moveCars();
			System.out.println(Print.carsPrint(cars));
		}
		
	}
}
