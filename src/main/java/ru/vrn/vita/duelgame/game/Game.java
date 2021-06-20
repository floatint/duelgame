package ru.vrn.vita.duelgame.game;

import java.util.List;
import java.util.Optional;

public interface Game {
    /**
     *  Перейти на следующий раунд
     * */
    void nextRound();
    void startGame();
}
