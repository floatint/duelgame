package ru.vrn.vita.duelgame.game;

import ru.vrn.vita.duelgame.config.GameConfig;
import ru.vrn.vita.duelgame.config.PlayerConfig;
import ru.vrn.vita.duelgame.io.InputProvider;
import ru.vrn.vita.duelgame.io.OutputProvider;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class GameImplementation implements Game {

    /**
     *  @param config Конфигурация игры
     *  @param playerInputProvider Провайдер ввода для игроков (реальных)
     *  @param playerOutputProvider Провайдер вывода для игроков (реальных). На случай, если игрок что-то хочет сказать...
     *  @param gameOutputProvider Провайдер вывода для самой игры. Вывод различных сообщений и т.п.
     * */
    public GameImplementation(GameConfig config, InputProvider<Integer> playerInputProvider, OutputProvider<String> playerOutputProvider, OutputProvider<String> gameOutputProvider){
        // одному играть не интересно
        if (config.getPlayers().size() < 2) {
            throw new RuntimeException("Too few players");
        }

        // сохраняем IO провайдеры
        this.outputProvider = gameOutputProvider;

        roundsCount = config.getRoundsCount();
        // создаем игроков
        List<Player> players = new LinkedList<>();
        for (PlayerConfig playerConfig : config.getPlayers()){
            if (playerConfig.isAI()){
                players.add(new AIPlayer(playerConfig.getName(), playerConfig.getCardsCount()));
            } else{
                players.add(new UserPlayer(playerConfig.getName(), playerConfig.getCardsCount(), playerInputProvider, playerOutputProvider));
            }
        }

        // рандомно определяем кто ходит первый
        Random rnd = new Random();
        int firstPlayerNum = rnd.nextInt(players.size());
        firstPlayer = players.get(firstPlayerNum);
        int secondPlayerNum = rnd.nextInt(players.size());
        while(firstPlayerNum == secondPlayerNum){
            secondPlayerNum = rnd.nextInt(players.size());
        }
        secondPlayer = players.get(secondPlayerNum);
    }

    private void nextRound() {
        try {
            // Очистка буффера вывода. В винде не работает.
            outputProvider.clear();
            outputProvider.output(String.format("========== ROUND %d ==========", currentRound));
            outputProvider.output(firstPlayer.getName() + " starting the round");
            // шаг первого игрока
            firstPlayer.handleRound();
            // получаем карту, выбранную пользователем
            Optional<Integer> firstPlayerCard = firstPlayer.getSelectedCard();
            if (!firstPlayerCard.isPresent()){
                throw new RuntimeException("First player's card is empty");
            }
            outputProvider.output(firstPlayer.getName() + " made a choice");
            outputProvider.output("\t\t[X]");
            outputProvider.output(secondPlayer.getName() + " turn");
            // шаг второго игрока
            secondPlayer.handleRound();
            Optional<Integer> secondPlayersCard = secondPlayer.getSelectedCard();
            if (!secondPlayersCard.isPresent()){
                throw new RuntimeException("Second player's card is empty");
            }
            outputProvider.output(secondPlayer.getName() + " made a choice");
            outputProvider.output("\t\t[X]");
            // вывод результат раунда
            outputProvider.output("All players have chosen cards...");
            outputProvider.output("\t\t[X] [X]");
            outputProvider.output("Show cards....");
            outputProvider.output(String.format("\t\t[%d] [%d]", firstPlayerCard.get(), secondPlayersCard.get()));

            if (firstPlayerCard.get().compareTo(secondPlayersCard.get()) > 0){
                outputProvider.output(String.format("%s won !", firstPlayer.getName()));
                // записываем штраф
                secondPlayer.setScore(secondPlayersCard.get() - firstPlayerCard.get());
            } else if (firstPlayerCard.get().compareTo(secondPlayersCard.get()) < 0){
                outputProvider.output(String.format("%s won !", secondPlayer.getName()));
                firstPlayer.setScore(firstPlayerCard.get() - secondPlayersCard.get());
            } else{
                outputProvider.output("Nobody won !");
            }
            // обмениваемся использованными картами
            firstPlayer.storeOpponentCard(secondPlayersCard.get());
            secondPlayer.storeOpponentCard(firstPlayerCard.get());
        } catch (Exception ex){
            // переброс исключения наверх, там лучше знают что делать
            throw new RuntimeException(ex);
        }
        // обмен местами
        Player tmp = firstPlayer;
        firstPlayer = secondPlayer;
        secondPlayer = tmp;
    }

    @Override
    public void startGame() {
        while (currentRound <= roundsCount) {
            nextRound();
            currentRound++;
        }
        // смотрим кто победил, включая ничью
        Player winner = firstPlayer.getScore() > secondPlayer.getScore() ? firstPlayer : firstPlayer.getScore() == secondPlayer.getScore() ? null : secondPlayer;

        try {
            if (winner != null){
                outputProvider.output(String.format("\n!!!   %s   WON   !!!", winner.getName()));
            } else{
                outputProvider.output("\n!!!   NOBODY   WON   !!!");
            }
        }catch (IOException ioEx){
            // отдаем наверх
            throw new RuntimeException(ioEx);
        }
    }

    private int currentRound = 1;
    private int roundsCount = 1;
    private Player firstPlayer;
    private Player secondPlayer;
    private OutputProvider<String> outputProvider;
}
