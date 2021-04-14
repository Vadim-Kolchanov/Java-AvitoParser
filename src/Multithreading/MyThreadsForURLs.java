package Multithreading;

import StartParse.StartParseURLs;
import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;

import java.util.Map;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class MyThreadsForURLs implements Threads {

    private final Map<Integer, NameOfCitiesAndURLs> indexesAndCities;
    private final String pathToFolderWithUrls;

    public MyThreadsForURLs(Map<Integer, NameOfCitiesAndURLs> indexesAndCities, String pathToFolderWithUrls) {
        this.indexesAndCities = indexesAndCities;
        this.pathToFolderWithUrls = pathToFolderWithUrls;
    }

    @Override
    public Thread createThread(Map.Entry<Integer, NameOfCitiesAndURLs> entry) {
        return new Thread(
                   new Multithreading(
                       new StartParseURLs(
                               entry.getValue(),
                               this.pathToFolderWithUrls
                       )
                   )
        );
    }

    @Override
    public void startThreads() {
        for (Map.Entry<Integer, NameOfCitiesAndURLs> entry: indexesAndCities.entrySet()) {
            createThread(entry).start();
        }
    }
}
