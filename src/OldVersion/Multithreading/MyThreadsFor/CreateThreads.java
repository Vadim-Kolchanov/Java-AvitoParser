package OldVersion.Multithreading.MyThreadsFor;

import OldVersion.ru.Avito.Parser.Cities.NameOfCitiesAndURLs;

import java.util.Map;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public interface CreateThreads {

    Thread createThread(Map.Entry<Integer, NameOfCitiesAndURLs> entry);

}
