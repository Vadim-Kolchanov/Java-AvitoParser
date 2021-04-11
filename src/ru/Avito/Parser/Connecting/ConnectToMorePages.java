package ru.Avito.Parser.Connecting;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class ConnectToMorePages implements Connect {

    private Connect connectToPage;
    private final NameOfCitiesAndURLs city;
    private final int limitPage;
    private int pagesLeft;

    public ConnectToMorePages(NameOfCitiesAndURLs city, int limitPage) throws IOException {
        this.connectToPage = new ConnectToPage(city);
        this.city = city;
        this.limitPage = this.pagesLeft = checkLimit(limitPage);

    }

    public ConnectToMorePages(NameOfCitiesAndURLs city) throws IOException {
        this.connectToPage = new ConnectToPage(city);
        this.city = city;
        this.limitPage = this.pagesLeft = getPagination();
    }

    public int checkLimit(int limitPage) throws IOException {
        int maxPage = getPagination();
        while (!(limitPage > 0 && limitPage < maxPage)) {
            System.out.println("\nВведите другое число для лимита страниц");
            System.out.println("Мин. число: 1, Макс. число: " + maxPage);
            System.out.print("Введите новое число: ");
            limitPage = new Scanner(System.in).nextInt();
        }
        return limitPage;
    }

    public int getPagination() throws IOException {
        int maxPage = 1;
        Elements elements = getBlockWithPageNumbers();
        String href = elements.get(elements.size() - 1).attr("href");
        if (href == null) {
            return maxPage;
        }
        URL url = new URL("https://www.avito.ru" + href);
        String query = url.getQuery();
        return Integer.parseInt(query.split("=")[1]);
    }

    public Elements getBlockWithPageNumbers() throws IOException {
        return connectToPage.getConnect()
                .getElementsByAttributeValue(
                    "class",
                    "pagination-pages clearfix"
                )
                .get(0)
                .children();
    }

    @Override
    public NameOfCitiesAndURLs getCity() {
        return connectToPage.getCity();
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
