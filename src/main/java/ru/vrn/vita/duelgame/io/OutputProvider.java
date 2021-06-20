package ru.vrn.vita.duelgame.io;

import java.io.IOException;

/**
 *  Интерфейс вывода данных
 * */
public interface OutputProvider<T> {
    /**
     *  Конкретная реализация может выводить текст в консоль
     *  или в графическое окошко или еще как-то.
     * */
    void output(T o) throws IOException;
    /**
     *  Очистка вывода
     * */
    void clear();
}
