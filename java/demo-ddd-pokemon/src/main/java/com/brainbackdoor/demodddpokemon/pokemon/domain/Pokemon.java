package com.brainbackdoor.demodddpokemon.pokemon.domain;

public class Pokemon {
    private int number;
    private String name;
    private String sprites;
    private int captureRate;

    public Pokemon(int number, String name, String sprites, int captureRate) {
        this.number = number;
        this.name = name;
        this.sprites = sprites;
        this.captureRate = captureRate;
    }

    //TODO: 잡혔는지 안잡혔는지 알 수 있어야 한다.
    public boolean isCaught(final double calculatedCaptureRate, final int number) {
        return number < calculatedCaptureRate;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getSprites() {
        return sprites;
    }
}
