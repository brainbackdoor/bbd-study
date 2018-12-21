package com.brainbackdoor.demodddpokemon.pokemon.domain;

public interface PokemonRepository {
    /*
        고수준 모듈
     */
    Pokemon findByNumber(int number);
}
