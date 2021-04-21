package OldVersion.ru.Avito.Parser.Pages;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import OldVersion.ru.Avito.Parser.Connecting.Connect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class PageWithApartments implements Page {

    private Connect connectToWebSite;

    public PageWithApartments(Connect connectToWebSite) {
        this.connectToWebSite = connectToWebSite;
    }

    @Override
    public Elements getElements() throws IOException {
        return this.connectToWebSite.getConnect()
                .getElementsByAttributeValue(
                        "class",
                        "iva-item-root-G3n7v photo-slider-slider-3tEix iva-item-list-2_PpT iva-item-redesign-1OBTh items-item-1Hoqq items-listItem-11orH items-redesignItem-1EDEr js-catalog-item-enum"
        );
    }

    @Override
    public List<String> getContent() throws IOException {
        List<String> listURLs = new ArrayList<String>();
        for (Element element: getElements()) {
            String href = element.getElementsByAttributeValue("class", "iva-item-titleStep-2bjuh")
                        .get(0)
                        .child(0)
                        .attr("href");
            listURLs.add(
                    "https://www.avito.ru"
                    + href
                    + "\n"
            );
        }
        return listURLs;
    }

}
