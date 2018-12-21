package com.brainbackdoor.demodddpokemon.trainer.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Trainer {

    @Id
    private String id;
    private int level;

    @Embedded
    private Party party;

    private Trainer() {
    }

    public Trainer(String id) {
        this.id = id;
        this.level = 1;
        this.party = new Party();
    }

    public void levelUp() {
        if (this.level < 30) this.level = this.level + 1;
    }

    public void gotPokemon(final PokemonCaught pokemonCaught) {
        this.party.add(pokemonCaught);
        levelUp();
    }

    public double getTrainerBonus() {
        if (this.level < 11) return 1.0;
        return this.level / 10.0;
    }
}
