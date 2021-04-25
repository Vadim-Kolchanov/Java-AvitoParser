package OldVersion.ru.Avito.Parser.Parsing.ParsingURLs;

import OldVersion.ru.Avito.Parser.Connecting.ConnectToWebSite;
import OldVersion.ru.Avito.Parser.Connecting.RetryConnectToWebSite;
import OldVersion.ru.Avito.Parser.ContentFile.ContentOfFile;
import OldVersion.ru.Avito.Parser.ContentFile.StorageContentOfFile;
import OldVersion.ru.Avito.Parser.Pages.PageWithApartments;
import OldVersion.ru.Avito.Parser.Parsing.Parse;
import OldVersion.ru.Avito.Parser.ReadAndWriteToFile.WriteReadFile;
import OldVersion.ru.Avito.Parser.actToCollection.ActToList;

import java.io.IOException;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class ParseURLs implements Parse {

    private final String urlToPageWithApartments;
    private final WriteReadFile writeReadFileWithURLs;

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

    @Override
    public String getContentParse() throws IOException {
        return new ActToList(
                   new ActToList(
                           new PageWithApartments(
                                   new RetryConnectToWebSite(
                                           new ConnectToWebSite(urlToPageWithApartments)
                                   )
                           ).getContent()
                   ).removeDuplicate(getHaveURLsNow())
        ).getStringOfList();
    }
}
