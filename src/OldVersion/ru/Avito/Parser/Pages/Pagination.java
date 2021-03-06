package OldVersion.ru.Avito.Parser.Pages;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import OldVersion.ru.Avito.Parser.Connecting.ConnectToWebSite;
import OldVersion.ru.Avito.Parser.Connecting.RetryConnectToWebSite;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class Pagination {

    private final String urlPage;

    public Pagination(String urlPage) {
        this.urlPage = urlPage;
    }

    public int checkLimit(int limitPage) {
        int maxPage = getPagination();
        if (maxPage == 0) {
            return 0;
        }
        while (!(limitPage > 0 && limitPage < maxPage)) {
            System.out.println("\nВведите другое число для лимита страниц");
            System.out.println("Мин. число: 1, Макс. число: " + maxPage);
            System.out.print("Введите новое число: ");
            limitPage = new Scanner(System.in)
                    .nextInt();
        }
        return limitPage;
    }

    public int getPagination() {
        try {
            Element element;
            if ((element = getBlockWithPageNumbers()) == null) {
                return 0;
            }
            Elements elementChildren = element.children();
            String href = elementChildren.get(
                    elementChildren.size() - 1
            ).attr("href");
            if (href.equals("")) {
                return 0;
            }
            String query = new URL("https://www.avito.ru" + href)
                    .getQuery();
            return Integer.parseInt(
                    query.split("=")[1]
            );
        } catch (IOException ex) {
            System.out.print("Error in getPagination: ");
            System.out.println(ex.getMessage());
            return 0;
        }

    }

    public Element getBlockWithPageNumbers() throws IOException {
        return new RetryConnectToWebSite(
                   new ConnectToWebSite(urlPage)
        ).getConnect(

        ).selectFirst(
                "div.pagination-hidden-3jtv4 div"
        );
    }
}
