package ru.Avito.Parser.Parsing.ParsingURLs;

import ru.Avito.Parser.Connecting.ConnectToWebSite;
import ru.Avito.Parser.Connecting.RetryConnectToWebSite;
import ru.Avito.Parser.ContentFile.ContentOfFile;
import ru.Avito.Parser.ContentFile.StorageContentOfFile;
import ru.Avito.Parser.MyException.AllPagesHaveBeenParsingException;
import ru.Avito.Parser.Pages.PageWithApartments;
import ru.Avito.Parser.Parsing.Parse;
import ru.Avito.Parser.ReadAndWriteToFile.WriteReadFile;
import ru.Avito.Parser.actToCollection.ActToList;

import java.io.IOException;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class ParseURLs implements Parse {

    private final String urlToPageWithApartments;
    private final WriteReadFile writeReadFileWithURLs;
    private List<String> haveURLsNow;

    public ParseURLs(String urlToPageWithApartments, WriteReadFile writeReadFileWithURLs) {
        this.urlToPageWithApartments = urlToPageWithApartments;
        this.writeReadFileWithURLs = writeReadFileWithURLs;

    }

    private List<String> getHaveURLsNow() throws IOException {
        return new StorageContentOfFile(
                new ContentOfFile(
                        writeReadFileWithURLs
                )
        ).getContent();
    }

    public List<String> removeDuplicate(List<String> content) throws IOException {
        if (this.haveURLsNow == null) {
            this.haveURLsNow = getHaveURLsNow();
        }
        content.removeAll(
                this.haveURLsNow
        );
        return content;
    }

    @Override
    public String getContentParse() throws IOException {
        return new ActToList(
                   removeDuplicate(
                           new PageWithApartments(
                                   new RetryConnectToWebSite(
                                           new ConnectToWebSite(urlToPageWithApartments)
                                   )
                           ).getContent()
                   )
        ).getStringOfList();
    }
}
