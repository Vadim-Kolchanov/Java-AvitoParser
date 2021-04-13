package ru.Avito.Parser.Parsing.ParsingApartment;

import ru.Avito.Parser.ContentFile.ContentFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class URLsNeedParsing {

    private final ContentFile contentOfFileWithURLs;
    private final ContentFile contentOfFileWithApartment;

    public URLsNeedParsing(ContentFile contentOfFileWithURLs, ContentFile contentOfFileWithApartment) {
        this.contentOfFileWithURLs = contentOfFileWithURLs;
        this.contentOfFileWithApartment = contentOfFileWithApartment;
    }

    public List<String> getURLsOfContentApartment() throws IOException {
        List<String> urls = new ArrayList<>();
        for (String line: contentOfFileWithApartment.getContent()) {
            urls.add(
                    line.substring(
                            line.lastIndexOf(";") + 1
                    )
            );
        }
        urls.remove(0);
        return urls;
    }

    public List<String> getURLsForParsing() throws IOException {
        List<String> urlsForParsing = contentOfFileWithURLs.getContent();
        urlsForParsing.removeAll(
                getURLsOfContentApartment()
        );
        return deleteAllEnterInList(urlsForParsing);
    }

    public List<String> deleteAllEnterInList(List<String> list) {
        List<String> newList = new ArrayList<>();
        for (String line: list) {
            newList.add(
                line.replace(
                        "\n",
                        ""
                )
            );
        }
        return newList;
    }




}
