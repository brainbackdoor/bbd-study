package com.brainbackdoor.demodddpokemon.trainer.domain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class PokemonCaught {
    private int number;
    private String nickName;

    private PokemonCaught() {
    }

    public PokemonCaught(int number, String nickName) {
        this.number = number;
        this.nickName = nickName;
    }

    public void changeNickName(final String nickName) {
        this.nickName = nickName;
    }
}
