package ru.vrn.vita.duelgame.cli;

import ru.vrn.vita.duelgame.io.InputProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Scanner;

/**
 *  Консольный ввод для игрока
 * */
public class PlayerInputProvider implements InputProvider<Integer> {
    @Override
    public Optional<Integer> input() throws IOException, NumberFormatException {
        return Optional.of(Integer.parseInt(reader.readLine()));
    }

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
}
