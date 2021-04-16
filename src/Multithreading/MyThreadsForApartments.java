package Multithreading;

import StartParse.StartParseParametersApartment;
import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;

import java.util.Map;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class MyThreadsForApartments implements CreateThreads {

    private final String pathToFolderWithUrls;
    private final String pathToFolderWithApartments;

    public MyThreadsForApartments(String pathToFolderWithUrls, String pathToFolderWithApartments) {
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
}
