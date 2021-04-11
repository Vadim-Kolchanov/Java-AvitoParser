package ru.Avito.Parser.Connecting;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class ConnectToPage implements Connect {

    private final NameOfCitiesAndURLs city;
    private final int pageNumber;

    ConnectToPage(NameOfCitiesAndURLs city) {
        this.city = city;
        this.pageNumber = 1;
    }

    ConnectToPage(NameOfCitiesAndURLs city, int pageNumber) {
        this.city = city;
        this.pageNumber = pageNumber;
    }

    @Override
    public String getURL() {
        return this.city.getURL() + "?p=" + this.pageNumber;
    }

    @Override
    public Document getConnect() throws IOException {
        return Jsoup.connect(getURL()).get();
    }

    @Override
    public NameOfCitiesAndURLs getCity() {
        return city;
    }
}
