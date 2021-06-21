package ru.vrn.vita.duelgame.game;

import java.util.*;

/**
 *  Абстрактный класс игрока.
 *  Имплементирует базовую логику для либого вида игрока.
 * */
public abstract class BasePlayer implements Player {

    BasePlayer(String name, int cardsCount) {
        this.name = name;
        for (int i = 0; i < cardsCount; i++){
            availableCards.add(i);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<Integer> getAvailableCards() {
        return availableCards;
    }

    @Override
    public void storeOpponentCard(Integer card) {
        opponentCards.add(card);
    }

    /**
     *  Получить список карт противника, которые он уже выложил
     * */
    protected Set<Integer> getOpponentCards(){
        return opponentCards;
    }

    @Override
    public Optional<Integer> getSelectedCard(){
        if (selectedCard != null){
            Integer tmp = selectedCard;
            selectedCard = null;
            return Optional.of(tmp);
        } else{
            return Optional.empty();
        }
    }

    protected void setSelectedCard(Integer card){
        if (availableCards.contains(card)){
            this.selectedCard = card;
            availableCards.remove(card);
        } else{
            throw new InputMismatchException("Invalid player card");
        }
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    private String name;
    private Set<Integer> availableCards = new TreeSet<>();
    private Set<Integer> opponentCards = new TreeSet<>();
    private Integer selectedCard;
    private int score;
}
