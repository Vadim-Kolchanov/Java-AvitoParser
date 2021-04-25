package OldVersion.ru.Avito.Parser.Parsing.ParsingApartment.ParametersApartment;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import OldVersion.ru.Avito.Parser.Pages.Page;
import OldVersion.ru.Avito.Parser.Parsing.ParsingApartment.BlockWithParameters;

import java.io.IOException;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class TotalArea implements Page {

    private final Page pageApartment;

    public TotalArea(Page pageApartment) {
        this.pageApartment = pageApartment;
    }

    @Override
    public Elements getElements() throws IOException {
        return this.pageApartment.getElements();
    }

    @Override
    public List<String> getContent() throws IOException {
        List<String> content = this.pageApartment.getContent();
        String totalArea = "";
        Element totalAreaElement = new BlockWithParameters(
                getElements().select("li.item-params-list-item"),
                "Общая площадь"
        ).getParameter();
        if (totalAreaElement != null) {
            totalArea = totalAreaElement.text()
                    .split(":")[1]
                    .trim()
                    .split(" ")[0];
        }
        content.add(totalArea + ";");
        return content;
    }
}
