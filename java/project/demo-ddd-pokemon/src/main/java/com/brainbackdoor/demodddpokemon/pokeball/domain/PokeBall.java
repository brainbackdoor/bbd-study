package com.brainbackdoor.demodddpokemon.pokeball.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PokeBall {
    @Id
    private int id;
    private String name;
    private double bonusMultiplier;

    public PokeBall() {
    }

    public PokeBall(int id, String name, double bonusMultiplier) {
        this.id = id;
        this.name = name;
        this.bonusMultiplier = bonusMultiplier;
    }

    public double getBonusMultiplier() {
        return bonusMultiplier;
    }
}
