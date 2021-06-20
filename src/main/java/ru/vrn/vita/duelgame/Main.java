package ru.vrn.vita.duelgame;

import ru.vrn.vita.duelgame.cli.GameOutputProvider;
import ru.vrn.vita.duelgame.cli.PlayerInputProvider;
import ru.vrn.vita.duelgame.cli.PlayerOutputProvider;
import ru.vrn.vita.duelgame.config.AppPropertiesConfig;
import ru.vrn.vita.duelgame.config.GameConfig;
import ru.vrn.vita.duelgame.game.Game;
import ru.vrn.vita.duelgame.game.GameImplementation;

import java.lang.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    // файл конфигурации игры
    private static final String PROPERTIES_FILE_NAME = "game.properties";

    public static void main(String[] args){
        try {
            // получаем конфигурацию
            GameConfig config = new AppPropertiesConfig(PROPERTIES_FILE_NAME);
            // получаем инстанс игры
            Game game = new GameImplementation(config, new PlayerInputProvider(), new PlayerOutputProvider(), new GameOutputProvider());
            // играем !
            game.startGame();
        } catch(Exception ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
