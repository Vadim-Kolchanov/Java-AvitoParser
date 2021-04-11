package ru.Avito.Parser.Pages;

import org.jsoup.select.Elements;
import ru.Avito.Parser.Connecting.ConnectCity;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class Pagination {

    private final ConnectCity connectCityToPage;

    public Pagination(ConnectCity connectCityToPage) throws IOException {
        this.connectCityToPage = connectCityToPage;
    }

    public int checkLimit(int limitPage) throws IOException {
        int maxPage = getPagination();
        while (!(limitPage > 0 && limitPage < maxPage)) {
            System.out.println("\nВведите другое число для лимита страниц");
            System.out.println("Мин. число: 1, Макс. число: " + maxPage);
            System.out.print("Введите новое число: ");
            limitPage = new Scanner(System.in)
                    .nextInt();
        }
        return limitPage;
    }

    public int getPagination() throws IOException {
        Elements elements = getBlockWithPageNumbers();
        String href = elements.get(elements.size() - 1)
                .attr("href");
        if (href.equals("")) {
            return 1;
        }
        String query = new URL("https://www.avito.ru" + href)
                .getQuery();
        return Integer.parseInt(
                query.split("=")[1]
        );
    }

    public Elements getBlockWithPageNumbers() throws IOException {
        return connectCityToPage.getConnect()
                .getElementsByAttributeValue(
                        "class",
                        "pagination-pages clearfix"
                )
                .get(0)
                .children();
    }

}