package OldVersion.ru.Avito.Parser.Parsing.ParsingApartment.ParametersApartment;

import org.jsoup.select.Elements;
import OldVersion.ru.Avito.Parser.Pages.Page;

import java.io.IOException;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class PriceApartment implements Page {

    private final Page pageApartment;

    public PriceApartment(Page pageApartment) {
        this.pageApartment = pageApartment;
    }

    @Override
    public Elements getElements() throws IOException {
        return this.pageApartment.getElements();
    }

    @Override
    public List<String> getContent() throws IOException {
        List<String> content = this.pageApartment.getContent();
        String price = "";
        Elements priceElement = getElements()
                .select("span.js-item-price");
        if (priceElement != null) {
            price = priceElement.attr("content");
        }
        content.add(price + ";");
        return content;
    }
}
