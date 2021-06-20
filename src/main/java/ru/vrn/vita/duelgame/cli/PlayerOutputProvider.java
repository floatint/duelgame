package ru.vrn.vita.duelgame.cli;

import ru.vrn.vita.duelgame.game.Player;
import ru.vrn.vita.duelgame.io.OutputProvider;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;

/**
 *  Консольный вывод для игрока
 * */
public class PlayerOutputProvider implements OutputProvider<String> {
    @Override
    public void clear() {
        throw new NotImplementedException();
    }

    @Override
    public void output(String o) throws IOException {
        System.out.println(o);
    }
}
