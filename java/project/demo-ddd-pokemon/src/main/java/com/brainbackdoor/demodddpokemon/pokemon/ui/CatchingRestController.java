package com.brainbackdoor.demodddpokemon.pokemon.ui;

import com.brainbackdoor.demodddpokemon.pokemon.application.CatchingResponse;
import com.brainbackdoor.demodddpokemon.pokemon.application.CatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatchingRestController {
    @Autowired
    private CatchingService catchingService;

    @GetMapping("/catch/{pokemonNumber}")
    public ResponseEntity<CatchingResponse> catchingPokemon(
        @RequestParam(value = "pokeBallId") int pokeBallId,
        @RequestParam(value = "trainerId") String trainerId,
        @PathVariable int pokemonNumber
    ) {
        return ResponseEntity.ok().body(catchingService.tryCatching(trainerId, pokemonNumber, pokeBallId));
    }
}
