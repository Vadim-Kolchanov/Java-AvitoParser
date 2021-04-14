package StartParse;

import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;
import ru.Avito.Parser.ReadAndWriteToFile.Prefix;
import ru.Avito.Parser.ReadAndWriteToFile.WriteParseToFile.WriteURLsToFile;
import ru.Avito.Parser.ReadAndWriteToFile.WriteReadFile;
import ru.Avito.Parser.ReadAndWriteToFile.WriteReadToFile;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class StartParseURLs implements StartParse {

    private final NameOfCitiesAndURLs city;
    private final String pathToFolderWithUrls;

    public StartParseURLs(NameOfCitiesAndURLs city, String pathToFolderWithUrls) {
        this.city = city;
        this.pathToFolderWithUrls = pathToFolderWithUrls;
    }

    public void startParsing() throws IOException {
        WriteReadFile writeReadToFileWithURLs = new WriteReadToFile(
                pathToFolderWithUrls,
                Prefix.URLS,
                city
        );
        new WriteURLsToFile(
                writeReadToFileWithURLs,
                1,
                city
        ).write();
    }
}
