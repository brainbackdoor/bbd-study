package com.brainbackdoor.demodddpokemon.pokemon.application;

import com.brainbackdoor.demodddpokemon.pokeball.domain.PokeBall;
import com.brainbackdoor.demodddpokemon.pokeball.domain.PokeBallRepository;
import com.brainbackdoor.demodddpokemon.pokemon.domain.CalculateService;
import com.brainbackdoor.demodddpokemon.pokemon.domain.Pokemon;
import com.brainbackdoor.demodddpokemon.pokemon.domain.PokemonRepository;
import com.brainbackdoor.demodddpokemon.trainer.domain.PokemonCaught;
import com.brainbackdoor.demodddpokemon.trainer.domain.Trainer;
import com.brainbackdoor.demodddpokemon.trainer.domain.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class CatchingService {

    @Autowired
    private PokemonRepository pokemonRepository;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private PokeBallRepository pokeBallRepository;

    @Transactional
    public CatchingResponse tryCatching(
            final String trainerId,
            final int pokemonNumber,
            final int pokeBallId) {

        final Trainer trainer = trainerRepository.findById(trainerId);
        final Pokemon pokemon = pokemonRepository.findByNumber(pokemonNumber);
        final PokeBall pokeBall = pokeBallRepository.findById(pokeBallId).orElse(null);

        final CalculateService calculateService = new CalculateService();
        final double calculatedCaptureRate = calculateService.calculatedCaptureRate(trainer, pokemon, pokeBall);
        final boolean caught = pokemon.isCaught(calculatedCaptureRate, new Random().nextInt(99) + 1);

        if (caught) {
            trainer.gotPokemon(new PokemonCaught(pokemon.getNumber(), pokemon.getName()));
        }
        return new CatchingResponse(caught);
    }
}
