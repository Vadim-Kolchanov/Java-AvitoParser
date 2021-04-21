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
public class NumberOfRooms implements Page {

    private final Page pageApartment;

    public NumberOfRooms(Page pageApartment) {
        this.pageApartment = pageApartment;
    }

    @Override
    public Elements getElements() throws IOException {
        return this.pageApartment.getElements();
    }

    @Override
    public List<String> getContent() throws IOException {
        List<String> content = this.pageApartment.getContent();
        String numberOfRooms = "";
        Element numberOfRoomsElement = new BlockWithParameters(
                getElements().select("li.item-params-list-item"),
                "Количество комнат"
        ).getParameter();
        if (numberOfRoomsElement != null) {
            numberOfRooms = numberOfRoomsElement.text()
                    .split(":")[1]
                    .trim();
            numberOfRooms = transformTextRoomInNumber(numberOfRooms);
        }
        content.add(numberOfRooms + ";");
        return content;
    }

    private String transformTextRoomInNumber(String numberOfRooms) {
        switch (numberOfRooms) {
            case "студия":
                return "1";
            case "своб. планировка":
                return "";
        }
        try {
            Integer.parseInt(numberOfRooms);
            return numberOfRooms;
        } catch (NumberFormatException ex) {
            return "";
        }
    }
}
