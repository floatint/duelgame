package ru.vrn.vita.duelgame.config;

/**
 *  Параметры игрока
 * */
public class PlayerConfig {

    public PlayerConfig(String name, boolean isAI, int cardsCount) {
        this.name = name;
        this.isAI = isAI;
        this.cardsCount = cardsCount;
    }

    public String getName() {
        return name;
    }

    public boolean isAI() {
        return isAI;
    }

    public int getCardsCount() {
        return cardsCount;
    }

    private String name;
    private boolean isAI;
    private int cardsCount;
}
