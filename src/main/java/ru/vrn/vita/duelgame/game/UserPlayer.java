package ru.vrn.vita.duelgame.game;

import ru.vrn.vita.duelgame.io.InputProvider;
import ru.vrn.vita.duelgame.io.OutputProvider;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserPlayer extends BasePlayer {

    public UserPlayer(String name, int cardsCount, InputProvider<Integer> inputProvider, OutputProvider<String> outputProvider){
        super(name, cardsCount);
        this.inputProvider = inputProvider;
        this.outputProvider = outputProvider;
    }

    @Override
    public void handleRound(){
        // сначала покажем доступные игроку карты
        StringBuilder sb = new StringBuilder();
        sb.append("Available cards:\n\t\t");
        for (Integer i : getAvailableCards()){
            sb.append("[").append(i.toString()).append("]");
            sb.append(" ");
        }
        try {
            outputProvider.output(sb.toString());
        }catch(IOException ioEx){
            throw new RuntimeException(ioEx);
        }

        Optional<Integer> card = Optional.empty();

        boolean success = false;
        while (!success){
            try{
                card = inputProvider.input();
                // подсистема ввода может вернуть и пустой Optional,
                // позможно истекло время ожидания и т.п
                if (!card.isPresent()){
                    throw new RuntimeException("Player's card is empty");
                }
                card.ifPresent(this::setSelectedCard);
                success = true;
            }catch(IOException ex){
                // если произошел сбой в IO подсистеме, то мы ничего не сможем сделать
                throw new RuntimeException(ex);
            }catch (InputMismatchException | NumberFormatException inputEx){
                // если пользователь ошибся с вводом номера карты
                success = false;
                try{
                    outputProvider.output("Please, retry to enter card number...");
                }catch (IOException ioEx){
                    throw new RuntimeException(ioEx);
                }
            }
        }

    }

    private InputProvider<Integer> inputProvider;
    private OutputProvider<String> outputProvider;
}
