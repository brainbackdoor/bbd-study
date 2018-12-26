package com.brainbackdoor.demodddpokemon.pokemon.intra;

import com.brainbackdoor.demodddpokemon.pokemon.domain.Pokemon;
import com.brainbackdoor.demodddpokemon.pokemon.domain.PokemonRepository;
import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Name;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import org.springframework.stereotype.Component;

@Component
public class PokeApiCall implements PokemonRepository {
    private final static int KO_ID = 3;
    @Override
    public Pokemon findByNumber(int number) {
        final PokeApi pokeApi = new PokeApiClient();
        final PokemonSpecies pokemonSpecies = pokeApi.getPokemonSpecies(number);
        final String pokemonName = pokemonSpecies.getNames()
                .stream()
                .filter(name -> name.getLanguage().getId() == KO_ID)
                .findFirst()
                .map(Name::getName)
                .get();
        final int captureRate = pokemonSpecies.getCaptureRate();
        final String url = pokeApi.getPokemon(number).getSprites().getFrontDefault();
        return null;
    }
}
