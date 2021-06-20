package ru.vrn.vita.duelgame.cli;

import ru.vrn.vita.duelgame.io.OutputProvider;

import java.io.IOException;

public class GameOutputProvider implements OutputProvider<String> {
    @Override
    public void output(String o) throws IOException {
        System.out.println(o);
    }

    /**
     *  К сожалению, не работает в винде
     * */
    @Override
    public void clear() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
