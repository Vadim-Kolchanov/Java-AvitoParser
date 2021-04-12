package ru.Avito.Parser.Pages;

import org.jsoup.select.Elements;
import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;
import ru.Avito.Parser.MyException.AllPagesHaveBeenParsingException;

import java.io.IOException;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class PageApartment implements Page {

    private final NameOfCitiesAndURLs city;
    private final URLsNeedParsing urls;

    public PageApartment(NameOfCitiesAndURLs city, URLsNeedParsing urlsNeedParsing) {
        this.city = city;
        this.urls = urlsNeedParsing;
    }

    @Override
    public Elements getElements() throws IOException, AllPagesHaveBeenParsingException {
        return null;
    }

    @Override
    public List<String> getContent() throws IOException, AllPagesHaveBeenParsingException {
        return null;
    }

}
