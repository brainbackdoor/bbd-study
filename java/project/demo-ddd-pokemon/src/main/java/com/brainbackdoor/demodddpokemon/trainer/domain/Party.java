package com.brainbackdoor.demodddpokemon.trainer.domain;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Party {

    @ElementCollection
    @CollectionTable(name = "PARTY")
    private List<PokemonCaught> pokemons;


    public boolean isEmpty() {
        return (this.pokemons == null || pokemons.isEmpty());
    }

    public void add(final PokemonCaught pokemonCaught) {
        if(isEmpty()) this.pokemons = new ArrayList<>();
        this.pokemons.add(pokemonCaught);
    }
}
