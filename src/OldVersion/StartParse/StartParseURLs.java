package OldVersion.StartParse;

import OldVersion.ru.Avito.Parser.Cities.NameOfCitiesAndURLs;
import OldVersion.ru.Avito.Parser.MyException.ParserFinishedException;
import OldVersion.ru.Avito.Parser.ReadAndWriteToFile.Prefix;
import OldVersion.ru.Avito.Parser.ReadAndWriteToFile.WriteParseToFile.WriteURLsToFile;
import OldVersion.ru.Avito.Parser.ReadAndWriteToFile.WriteReadFile;
import OldVersion.ru.Avito.Parser.ReadAndWriteToFile.WriteReadToFile;

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
        try {
            new WriteURLsToFile(
                    writeReadToFileWithURLs,
                    1,
                    city
            ).write();
        } catch (ParserFinishedException ex) {
            System.out.println("\nParse URLs is finished! For city: " + city.name());
        }

    }
}
