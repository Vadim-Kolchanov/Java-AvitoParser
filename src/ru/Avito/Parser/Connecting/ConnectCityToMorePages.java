package ru.Avito.Parser.Connecting;

import org.jsoup.nodes.Document;
import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;
import ru.Avito.Parser.Pages.Pagination;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class ConnectCityToMorePages implements ConnectCity {

    private ConnectCity connectCityToPage;
    private final NameOfCitiesAndURLs city;
    private int pagesLeft;

    public ConnectCityToMorePages(NameOfCitiesAndURLs city, int limitPage) throws IOException {
        this.connectCityToPage = new ConnectCityToPage(city);
        this.city = city;
        this.pagesLeft = new Pagination(this.connectCityToPage)
                .checkLimit(limitPage);

    }

    public ConnectCityToMorePages(NameOfCitiesAndURLs city) throws IOException {
        this.connectCityToPage = new ConnectCityToPage(city);
        this.city = city;
        this.pagesLeft = new Pagination(this.connectCityToPage)
                .getPagination();
    }

    @Override
    public String getNameCity() {
        return connectCityToPage.getNameCity();
    }

    @Override
    public Document getConnect() throws IOException {
        if (this.pagesLeft > 0) {
            System.out.println("Номер страницы: " + this.pagesLeft);
            this.pagesLeft--;
            this.connectCityToPage = new ConnectCityToPage(this.city, this.pagesLeft);
            return this.connectCityToPage.getConnect();
        }
        return null;
    }
}
