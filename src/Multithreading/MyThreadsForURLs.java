package Multithreading;

import StartParse.StartParseURLs;
import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;

import java.util.Map;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class MyThreadsForURLs implements CreateThreads {

    private final String pathToFolderWithUrls;

    public MyThreadsForURLs(String pathToFolderWithUrls) {
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
}
