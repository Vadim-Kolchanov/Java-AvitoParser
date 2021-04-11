package ru.Avito.Parser.Pages;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.Avito.Parser.Connecting.Connect;
import ru.Avito.Parser.MyException.AllPagesHaveBeenParsing;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class PageWithApartments implements Page {

    private Connect connect;

    public PageWithApartments(Connect connect) {
        this.connect = connect;
    }

    @Override
    public Elements getElements() throws IOException, AllPagesHaveBeenParsing {
        Document connectToPage = connect.getConnect();
        if (connectToPage == null) throw new AllPagesHaveBeenParsing();
        return connectToPage.getElementsByAttributeValue(
                "class",
                "iva-item-root-G3n7v photo-slider-slider-3tEix iva-item-list-2_PpT iva-item-redesign-1OBTh items-item-1Hoqq items-listItem-11orH items-redesignItem-1EDEr js-catalog-item-enum"
        );
    }

    @Override
    public StringBuilder getContent() throws IOException, AllPagesHaveBeenParsing {
        StringBuilder listURLs = new StringBuilder();
        for (Element element: getElements()) {
            String href = element.getElementsByAttributeValue("class", "iva-item-titleStep-2bjuh")
                        .get(0)
                        .child(0)
                        .attr("href");
            listURLs.append("https://www.avito.ru").append(href).append("\n");
        }
        return listURLs;
    }

    @Override
    public String getNameCity() {
        return connect.getNameCity();
    }
}
