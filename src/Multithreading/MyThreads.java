package Multithreading;

import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class MyThreads implements StartThreads {

    private final Map<Integer, NameOfCitiesAndURLs> indexesAndCities;
    private final CreateThreads threadsFor;

    public MyThreads(Map<Integer, NameOfCitiesAndURLs> indexesAndCities, CreateThreads threadsFor) {
        this.indexesAndCities = indexesAndCities;
        this.threadsFor = threadsFor;
    }

    @Override
    public List<Thread> startThreads() {
        List<Thread> listThread = new ArrayList<>();
        Thread thread;
        for (Map.Entry<Integer, NameOfCitiesAndURLs> entry: indexesAndCities.entrySet()) {
            thread = this.threadsFor
                    .createThread(
                            entry
                    );
            thread.start();
            listThread.add(thread);
        }
        return listThread;
    }
}
