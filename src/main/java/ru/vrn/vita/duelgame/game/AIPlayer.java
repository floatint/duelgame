package ru.vrn.vita.duelgame.game;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
     *
     *  Поскольку мы знаем максимальное кол-во карт, то проверяем
     *  на сколько "старшие" карты выложил противник.
     *  Если противник в самом начале выкладывает "старшие" карты,
     *  то можно их пропустить, отдавая
     * */
    @Override
    public void handleRound() {

        Set<Integer> oppenentCards = getOpponentCards();
        Set<Integer> availableCards = getAvailableCards();

        // Выдаем карты по порядку
        if (getAvailableCards().iterator().hasNext()){
            setSelectedCard(getAvailableCards().iterator().next());
        }

/*        // просто выберем рандомную карту
        Random rnd = new Random();
        Integer card = rnd.nextInt(getAvailableCards().size());
        while (alreadyUsedCards.contains(card)){
            card = rnd.nextInt(getAvailableCards().size());
        }
        setSelectedCard(card);
        alreadyUsedCards.add(card);*/

    }

    private Set<Integer> alreadyUsedCards = new HashSet<>();
}
