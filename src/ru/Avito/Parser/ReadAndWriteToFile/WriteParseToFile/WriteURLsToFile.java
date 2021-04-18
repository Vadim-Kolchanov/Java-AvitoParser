package ru.Avito.Parser.ReadAndWriteToFile.WriteParseToFile;

import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;
import ru.Avito.Parser.MyException.ParserFinishedException;
import ru.Avito.Parser.Pages.Pagination;
import ru.Avito.Parser.Parsing.ParsingURLs.ParseURLs;
import ru.Avito.Parser.ReadAndWriteToFile.WriteReadFile;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class WriteURLsToFile {

    private final WriteReadFile writeReadToFile;
    private final NameOfCitiesAndURLs city;
    private int pagesLeft;

    public WriteURLsToFile(WriteReadFile writeReadToFile, int limit, NameOfCitiesAndURLs city) {
        this.writeReadToFile = writeReadToFile;
        this.pagesLeft = new Pagination(city.getURL())
                    .checkLimit(limit);
        this.city = city;
    }

    public WriteURLsToFile(WriteReadFile writeReadToFile, NameOfCitiesAndURLs city) {
        this.writeReadToFile = writeReadToFile;
        this.pagesLeft = new Pagination(city.getURL())
                     .getPagination();
        this.city = city;
    }

    public void write() throws IOException, ParserFinishedException {
        try {
            do {
                writeReadToFile.write(
                        new ParseURLs(
                                city.getURL() + "?p=" + pagesLeft,
                                writeReadToFile
                        ).getContentParse(),
                        true
                );
                Thread.sleep(3000);
            }
            while (--pagesLeft > 0);
            throw new ParserFinishedException();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
