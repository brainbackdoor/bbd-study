package com.brainbackdoor.bowling;

import lombok.Getter;

@Getter
public class Board {

    Player player;

    public void print() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
    }

    public void input(String name) {
        player = new Player(name);
    }

    public String frame() {
        return "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    }

    public String game() {
        StringBuilder sb = new StringBuilder();
        sb.append("|  ").append(player.getName()).append(" |      |      |      |      |      |      |      |      |      |      |");
        return sb.toString();
    }

    public String result(int i) {

        return "X  ";
    }
}
