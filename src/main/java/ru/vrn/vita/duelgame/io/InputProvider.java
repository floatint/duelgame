package ru.vrn.vita.duelgame.io;

import java.io.IOException;
import java.util.Optional;

/**
 *  Интерфейс ввода данных
 * */
public interface InputProvider<T> {
    /**
     *  Конкретная реализация может представлять собой ввод с консоли,
     *  обработку клика в окошке и т.п.
     * */
    Optional<T> input() throws IOException;
}
