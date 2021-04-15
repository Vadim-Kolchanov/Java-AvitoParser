package ru.Avito.Parser.Parsing.ParsingApartment.ParametersApartment;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.Avito.Parser.Pages.Page;
import ru.Avito.Parser.Parsing.ParsingApartment.BlockWithParameters;

import java.io.IOException;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class NumberOfFloors implements Page {

    private final Page pageApartment;

    public NumberOfFloors(Page pageApartment) {
        this.pageApartment = pageApartment;
    }

    @Override
    public Elements getElements() throws IOException {
        return this.pageApartment.getElements();
    }

    @Override
    public List<String> getContent() throws IOException {
        List<String> content = this.pageApartment.getContent();
        String numberOfFloors = "";
        Element floorElement = new BlockWithParameters(
                getElements().select("li.item-params-list-item"),
                "Этаж"
        ).getParameter();
        if (floorElement != null) {
            numberOfFloors = floorElement.text()
                    .split(" ")[3]
                    .trim();
        }
        content.add(numberOfFloors + ";");
        return content;
    }
}
