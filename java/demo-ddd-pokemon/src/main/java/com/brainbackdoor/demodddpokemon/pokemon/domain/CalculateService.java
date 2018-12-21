package com.brainbackdoor.demodddpokemon.pokemon.domain;

import com.brainbackdoor.demodddpokemon.pokeball.domain.PokeBall;
import com.brainbackdoor.demodddpokemon.trainer.domain.Trainer;

/**
 * 포획률 = (포켓몬의 포획률 * 몬스터볼 보정 승수 * (레벨 11 이상의 트레이너 레벨 / 10)) * 100 / 255
 * Domain Service는 Spring 과는 독립적이어야 한다.
 */
public class CalculateService {
    public double calculatedCaptureRate(
            final Trainer trainer
            , final Pokemon pokemon
            , final PokeBall pokeBall) {

        final double calculatedCaptureRate =
                pokemon.getCaptureRate() * pokeBall.getBonusMultiplier() * trainer.getTrainerBonus() * 100 / 255;
        if (calculatedCaptureRate < 100) return calculatedCaptureRate;
        return 100.0;
    }
}
