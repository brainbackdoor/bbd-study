package com.brainbackdoor.demodddpokemon.pokemon.application;

public class CatchingResponse {
    private boolean caught;

    public CatchingResponse(boolean caught) {
        this.caught = caught;
    }

    public boolean isCaught() {
        return caught;
    }
}
