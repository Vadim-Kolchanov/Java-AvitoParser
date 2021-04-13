package ru.Avito.Parser.ReadAndWriteToFile.WriteParseToFile;

import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;
import ru.Avito.Parser.ContentFile.ContentOfFile;
import ru.Avito.Parser.ContentFile.StorageContentOfFile;
import ru.Avito.Parser.MyException.AllPagesHaveBeenParsingException;
import ru.Avito.Parser.Pages.Pagination;
import ru.Avito.Parser.Parsing.ParsingURLs.ParseURLs;
import ru.Avito.Parser.ReadAndWriteToFile.WriteReadFile;
import ru.Avito.Parser.actToCollection.ActToList;

import java.io.IOException;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class WriteURLsToFile {

    private final WriteReadFile writeReadToFile;
    private final NameOfCitiesAndURLs city;
    private int pagesLeft;
    private final List<String> haveURLsNow;

    public WriteURLsToFile(WriteReadFile writeReadToFile, int limit, NameOfCitiesAndURLs city) throws IOException {
        this.writeReadToFile = writeReadToFile;
        this.pagesLeft = new Pagination(city.getURL())
                    .checkLimit(limit);
        this.city = city;
        this.haveURLsNow = getHaveURLsNow();
    }

    public WriteURLsToFile(WriteReadFile writeReadToFile, NameOfCitiesAndURLs city) throws IOException {
        this.writeReadToFile = writeReadToFile;
        this.pagesLeft = new Pagination(city.getURL())
                     .getPagination();
        this.city = city;
        this.haveURLsNow = getHaveURLsNow();
    }

    public List<String> getHaveURLsNow() throws IOException {
        return new StorageContentOfFile(
                   new ContentOfFile(
                        writeReadToFile
                   )
        ).getContent();
    }

    public void write() throws IOException {
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
            while (--pagesLeft != 0);
            throw new AllPagesHaveBeenParsingException();
        } catch (AllPagesHaveBeenParsingException ex) {
            System.out.println("Parser is finished!");
            System.out.println(ex.getMessage());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
