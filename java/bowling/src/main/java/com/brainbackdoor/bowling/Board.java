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

    public String resultShow() {
        StringBuilder sb = new StringBuilder();
        sb.append("|  ").append(player.getName()).append(" |").append(player.getResult());
        return sb.toString();
    }

    public String scoreShow() {
        StringBuilder sb = new StringBuilder();
        sb.append("|      |").append(player.getScore());
        return sb.toString();
    }

    public void game() throws Exception {
        for (int i = 0; i < player.getMaxTurn(); i++) player.bowling();
        if(player.isSpare()) player.bowling();
        if(player.isStrike()) {
            player.bowling();
            player.bowling();
        }
    }
}
