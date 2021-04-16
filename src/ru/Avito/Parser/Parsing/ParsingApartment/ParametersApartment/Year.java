package ru.Avito.Parser.Parsing.ParsingApartment.ParametersApartment;

import org.jsoup.select.Elements;
import ru.Avito.Parser.Pages.Page;
import ru.Avito.Parser.Parsing.ParsingApartment.DatePublished;

import java.io.IOException;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class Year implements Page {

    private final Page pageApartment;

    public Year(Page pageApartment) {
        this.pageApartment = pageApartment;
    }

    @Override
    public Elements getElements() throws IOException {
        return this.pageApartment.getElements();
    }

    @Override
    public List<String> getContent() throws IOException {
        List<String> content = this.pageApartment.getContent();
        String year = "";
        String[] dayMonthYear = new DatePublished(
                getElements()
        ).getDatePublished();
        if (dayMonthYear != null) {
            year = dayMonthYear[2];
        }
        content.add(year + ";");
        return content;
    }
}
