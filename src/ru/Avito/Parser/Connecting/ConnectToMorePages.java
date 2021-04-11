package ru.Avito.Parser.Connecting;

import org.jsoup.nodes.Document;
import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;

import java.io.IOException;
import java.util.Scanner;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class ConnectToMorePages implements Connect {

    private Connect connectToPage;
    private final NameOfCitiesAndURLs city;
    private int pagesLeft;

    public ConnectToMorePages(NameOfCitiesAndURLs city, int limitPage) throws IOException {
        this.connectToPage = new ConnectToPage(city);
        this.city = city;
        this.pagesLeft = new Pagination(this.connectToPage)
                .checkLimit(limitPage);

    }

    public ConnectToMorePages(NameOfCitiesAndURLs city) throws IOException {
        this.connectToPage = new ConnectToPage(city);
        this.city = city;
        this.pagesLeft = new Pagination(this.connectToPage)
                .getPagination();
    }

    @Override
    public String getNameCity() {
        return connectToPage.getNameCity();
    }

    @Override
    public String getURL() {
        return connectToPage.getURL();
    }

    @Override
    public Document getConnect() throws IOException {
        if (this.pagesLeft > 0) {
            System.out.println("Номер страницы: " + this.pagesLeft);
            this.pagesLeft--;
            this.connectToPage = new ConnectToPage(this.city, this.pagesLeft);
            return this.connectToPage.getConnect();
        }
        return null;
    }
}
