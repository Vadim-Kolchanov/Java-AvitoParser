package Multithreading;

import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;

import java.util.Map;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public interface Threads {

    Thread createThread(Map.Entry<Integer, NameOfCitiesAndURLs> entry);

    void startThreads();
}
