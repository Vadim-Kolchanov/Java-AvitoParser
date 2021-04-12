package ru.Avito.Parser.Connecting;

import org.jsoup.nodes.Document;
import ru.Avito.Parser.Pages.Pagination;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class ConnectCityToMorePages implements ConnectCity {

    private ConnectCity connectCityToPage;
    private int pagesLeft;

    public ConnectCityToMorePages(int limitPage, ConnectCity connectCity) throws IOException {
        this.connectCityToPage = connectCity;
        this.pagesLeft = new Pagination(this.connectCityToPage)
                .checkLimit(limitPage);
    }

    public ConnectCityToMorePages(ConnectCity connectCity) throws IOException {
        this.connectCityToPage = connectCity;
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
            this.connectCityToPage = new ConnectCityToPage(getNameCity(), this.pagesLeft);
            this.pagesLeft--;
            return this.connectCityToPage.getConnect();
        }
        return null;
    }
}
