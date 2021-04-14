package Multithreading;

import StartParse.StartParseParametersApartment;
import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;

import java.util.Map;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class MyThreadsForApartments implements Threads {

    private final Map<Integer, NameOfCitiesAndURLs> indexesAndCities;
    private final String pathToFolderWithUrls;
    private final String pathToFolderWithApartments;

    public MyThreadsForApartments(Map<Integer, NameOfCitiesAndURLs> indexesAndCities, String pathToFolderWithUrls, String pathToFolderWithApartments) {
        this.indexesAndCities = indexesAndCities;
        this.pathToFolderWithUrls = pathToFolderWithUrls;
        this.pathToFolderWithApartments = pathToFolderWithApartments;
    }

    @Override
    public Thread createThread(Map.Entry<Integer, NameOfCitiesAndURLs> entry) {
        return new Thread(
                new Multithreading(
                        new StartParseParametersApartment(
                                entry.getKey(),
                                entry.getValue(),
                                this.pathToFolderWithUrls,
                                this.pathToFolderWithApartments
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
