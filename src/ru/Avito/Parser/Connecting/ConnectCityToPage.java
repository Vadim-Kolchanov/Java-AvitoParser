package ru.Avito.Parser.Connecting;

import org.jsoup.nodes.Document;
import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class ConnectCityToPage implements ConnectCity {

    private final NameOfCitiesAndURLs city;
    private final int pageNumber;

    ConnectCityToPage(NameOfCitiesAndURLs city) {
        this.city = city;
        this.pageNumber = 1;
    }

    ConnectCityToPage(NameOfCitiesAndURLs city, int pageNumber) {
        this.city = city;
        this.pageNumber = pageNumber;
    }

    @Override
    public Document getConnect() throws IOException {
        String url = this.city.getURL() + "?p=" + this.pageNumber;
        return new RetryConnectToWebSite(
                 new ConnectToWebSite(url)
            ).getConnect();
    }

    @Override
    public String getNameCity() {
        return city.name();
    }
}
