package ru.vrn.vita.duelgame.config;


import java.util.List;

/**
 *  общий интерфейс кофигурации для различных форматов.
 * */
public interface GameConfig {
    /**
     *  Получить кол-во раундов в игре
     * */
    int getRoundsCount();
    /**
     *  Получить список игроков
     * */
    List<PlayerConfig> getPlayers();
}
