package ru.vrn.vita.duelgame.game;

import java.util.Optional;
import java.util.Set;

public interface Player {

    /**
     *  Получить имя игрока
     * */
    String getName();

    /**
     *  Получить список доступных для игрока карт.
     * */
    Set<Integer> getAvailableCards();

    /**
     *  Сохранить очередную карту противника
     * */
    void storeOpponentCard(Integer card);

    /**
     *  Вернуть выбранную игроком карту
     * */
    Optional<Integer> getSelectedCard();

    /**
     *  Обработка раунда игры.
     *  Объект Player ничего не знает о текущем раунде.
     *  Все что он может - выбрать как либо карту, которую
     *  потом можно получить через Integer getSelectedCard();
     */
    void handleRound();

    /**
     *  Получить счет игрока
     * */
    int getScore();

    /**
     *  Установить счет игрока
     * */
    void setScore(int score);
}
