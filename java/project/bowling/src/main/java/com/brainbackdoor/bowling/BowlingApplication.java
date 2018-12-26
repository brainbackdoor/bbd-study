package com.brainbackdoor.bowling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class BowlingApplication {

    public static void main(String[] args) throws Exception {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        board.print();
        board.input(scanner.nextLine());
        board.game();
        System.out.println(board.frame());
        System.out.println(board.resultShow());
        System.out.println(board.scoreShow());
        scanner.close();
    }

}