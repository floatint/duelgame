package ru.vrn.vita.duelgame.config;

import ru.vrn.vita.duelgame.game.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Класс конфигурации из *.properties файлов.
 * */
public class AppPropertiesConfig implements GameConfig {

    private static final String ROUNDS_COUNT_PROP = "roundsCount";
    private static final String PLAYERS_COUNT_PROP = "playersCount";
    private static final String PLAYER_NAME_PROP = "player_%d.name";
    private static final String PLAYER_IS_AI_PROP = "player_%d.isAI";

    public AppPropertiesConfig(String propertiesFileName){
        Properties props = new Properties();
        try{
            props.load(getClass().getClassLoader().getResourceAsStream(propertiesFileName));
            roundsCount = Integer.parseInt(props.getProperty(ROUNDS_COUNT_PROP));
            int playersCount = Integer.parseInt(props.getProperty(PLAYERS_COUNT_PROP));
            for (int i = 1; i <= playersCount; i++){
                String playerName = props.getProperty(String.format(PLAYER_NAME_PROP, i));
                boolean isAIPlayer = Boolean.parseBoolean(props.getProperty(String.format(PLAYER_IS_AI_PROP, i)));

                players.add(new PlayerConfig(playerName, isAIPlayer, roundsCount));
            }
        }catch (IOException | NumberFormatException ex){
            throw new RuntimeException(ex);
        }
    }
    @Override
    public int getRoundsCount() {
        return roundsCount;
    }

    @Override
    public List<PlayerConfig> getPlayers() {
        return players;
    }

    private List<PlayerConfig> players = new LinkedList<>();
    private int roundsCount = 0;
}
