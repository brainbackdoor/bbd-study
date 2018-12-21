package com.brainbackdoor.demodddpokemon.pokemon.application;

import com.brainbackdoor.demodddpokemon.pokeball.domain.PokeBall;
import com.brainbackdoor.demodddpokemon.pokeball.domain.PokeBallRepository;
import com.brainbackdoor.demodddpokemon.pokemon.domain.Pokemon;
import com.brainbackdoor.demodddpokemon.pokemon.domain.PokemonRepository;
import com.brainbackdoor.demodddpokemon.trainer.domain.Trainer;
import com.brainbackdoor.demodddpokemon.trainer.domain.TrainerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CatchingServiceTest {

    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private PokemonRepository pokemonRepository;

    @Mock
    private PokeBallRepository pokeBallRepository;

    @InjectMocks
    private CatchingService catchingService;

    @Test
    public void masterBall() {
        // given
        given(trainerRepository.findById(anyString()))
                .willReturn(new Trainer("test"));

        given(pokemonRepository.findByNumber(anyInt()))
                .willReturn(new Pokemon(1, "", null, 1));

        given(pokeBallRepository.findById(1))
                .willReturn(Optional.of(new PokeBall(1, "마스터볼", 255)));

        // when
        final CatchingResponse caught = catchingService.tryCatching("test", 1, 1);
        // then
        assertThat(caught.isCaught(), is(true));
    }
}