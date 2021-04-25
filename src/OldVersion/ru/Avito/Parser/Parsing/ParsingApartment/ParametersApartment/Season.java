package OldVersion.ru.Avito.Parser.Parsing.ParsingApartment.ParametersApartment;

import org.jsoup.select.Elements;
import OldVersion.ru.Avito.Parser.Pages.Page;
import OldVersion.ru.Avito.Parser.Parsing.ParsingApartment.DatePublished;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class Season implements Page {

    private final boolean convertTextToNumber;
    private final Page pageApartment;

    public Season(boolean convertTextToNumber, Page pageApartment) {
        this.convertTextToNumber = convertTextToNumber;
        this.pageApartment = pageApartment;
    }

    @Override
    public Elements getElements() throws IOException {
        return this.pageApartment.getElements();
    }

    @Override
    public List<String> getContent() throws IOException {
        List<String> content = this.pageApartment.getContent();
        String season = "";
        String[] dayMonthYear = new DatePublished(
                getElements()
        ).getDatePublished();
        if (dayMonthYear != null) {
            season = transformDatePublishedInSeasonText(
                    dayMonthYear[1]
            );
            season = convertTextToNumber ? transformTextSeasonInNumber(season) : season;
        }
        content.add(season + ";");
        return content;
    }

    private String transformTextSeasonInNumber(String season) {
        List<String> nameSeasons = Arrays.asList(
                "Winter",
                "Spring",
                "Summer",
                "Autumn"
        );
        return String.valueOf(
                nameSeasons.indexOf(season) + 1
        );
    }

    private String transformDatePublishedInSeasonText(String month) {
        try {
            int monthNumber = Integer.parseInt(month);
            if (monthNumber > 2 && monthNumber < 6) {
                return "Spring";
            }
            if (monthNumber > 5 && monthNumber < 9) {
                return "Summer";
            }
            if (monthNumber > 8 && monthNumber < 12) {
                return "Autumn";
            }
            return "Winter";
        } catch (NumberFormatException ex) {
            return "";
        }
    }
}
