package ru.vrn.vita.duelgame.game;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  Класс игрока-компьютера
 * */
public class AIPlayer extends BasePlayer {

    public AIPlayer(String name, int cardsCount){
        super(name, cardsCount);
    }

    /**
     *  Алгоритм работы AI следующий:
     *  1) Получаем список карт, которые противник уже выложил.
     *  2) Получаем список карт, которые доступны AI в текущем раунде.
     *  3) Вычисляем среднее значение карты на основе уже использованных противником карт.
     *     Если таковых нет, то берется одна из доступных для AI карт.
     *  4) Выбираем карту, которая > среднего значения, либо если такой нет, то самую первую.
     * */
    @Override
    public void handleRound() {
        Random rnd = new Random();
        List<Integer> opponentCards = new ArrayList<>(getOpponentCards());
        List<Integer> availableCards = new ArrayList<>(getAvailableCards());
        double avgCard = opponentCards.stream().mapToInt(x -> x).average().orElse(rnd.nextInt(availableCards.size()));
        setSelectedCard(availableCards.stream().filter(x -> x > (int)avgCard).findFirst().orElse(availableCards.get(0)));
    }
}
