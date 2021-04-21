package OldVersion.StartParse;

import OldVersion.ru.Avito.Parser.Cities.NameOfCitiesAndURLs;
import OldVersion.ru.Avito.Parser.ContentFile.ContentOfFile;
import OldVersion.ru.Avito.Parser.ContentFile.StorageContentOfFile;
import OldVersion.ru.Avito.Parser.MyException.ParserFinishedException;
import OldVersion.ru.Avito.Parser.Parsing.ParsingApartment.URLsNeedParsing;
import OldVersion.ru.Avito.Parser.ReadAndWriteToFile.Prefix;
import OldVersion.ru.Avito.Parser.ReadAndWriteToFile.WriteParseToFile.WriteApartmentsToFile;
import OldVersion.ru.Avito.Parser.ReadAndWriteToFile.WriteReadFile;
import OldVersion.ru.Avito.Parser.ReadAndWriteToFile.WriteReadToFile;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class StartParseParametersApartment implements StartParse {

    private final int indexCity;
    private final NameOfCitiesAndURLs city;
    private final String pathToFolderWithUrls;
    private final String pathToFolderWithApartments;

    public StartParseParametersApartment(int indexCity, NameOfCitiesAndURLs city, String pathToFolderWithUrls, String pathToFolderWithApartments) {
        this.indexCity = indexCity;
        this.city = city;
        this.pathToFolderWithUrls = pathToFolderWithUrls;
        this.pathToFolderWithApartments = pathToFolderWithApartments;
    }

    public void startParsing() throws IOException {
        WriteReadFile writeReadToFileWithApartments = new WriteReadToFile(
                pathToFolderWithApartments,
                Prefix.APARTMENT,
                city
        );
        WriteReadFile writeReadToFileWithURLs = new WriteReadToFile(
                pathToFolderWithUrls,
                Prefix.URLS,
                city
        );
        try {
            new WriteApartmentsToFile(
                    indexCity,
                    writeReadToFileWithApartments,
                    new URLsNeedParsing(
                            new StorageContentOfFile(
                                    new ContentOfFile(
                                            writeReadToFileWithURLs
                                    )
                            ),
                            new StorageContentOfFile(
                                    new ContentOfFile(
                                            writeReadToFileWithApartments
                                    )
                            )
                    )
            ).write();
        } catch (ParserFinishedException ex) {
            System.out.println("\nParse Apartments is finished! For IndexCity: " + indexCity);
        }
    }
}
