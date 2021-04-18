package ru.Avito.Parser.Parsing.ParsingApartment.ParametersApartment;

import org.jsoup.select.Elements;
import ru.Avito.Parser.Pages.Page;

import java.io.IOException;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class CoordinatesApartment implements Page {

    private final Page pageApartment;

    public CoordinatesApartment(Page pageApartment) {
        this.pageApartment = pageApartment;
    }

    @Override
    public Elements getElements() throws IOException {
        return this.pageApartment.getElements();
    }

    @Override
    public List<String> getContent() throws IOException {
        List<String> content = this.pageApartment.getContent();
        String lat = "",
               lon = "";
        Elements latAndLon = getElements()
                .select("div.b-search-map.expanded.item-map-wrapper.js-item-map-wrapper");
        if (latAndLon != null) {
            lat = latAndLon.attr("data-map-lat");
            lon = latAndLon.attr("data-map-lon");
        }
        content.add(lat + ";");
        content.add(lon + ";");
        return content;
    }
}
