package com.brainbackdoor.demodddpokemon.trainer.domain;

public interface TrainerRepository {
    Trainer findById(String id);
    void save(Trainer trainer);
}
