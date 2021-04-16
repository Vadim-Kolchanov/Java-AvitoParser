package ru.Avito.Parser.Parsing.ParsingApartment.ParametersApartment;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.Avito.Parser.Pages.Page;
import ru.Avito.Parser.Parsing.ParsingApartment.BlockWithParameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class TypeWalls implements Page {

    private final boolean convertTextToNumber;
    private final Page pageApartment;

    public TypeWalls(boolean convertTextToNumber, Page pageApartment) {
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
        String typeWalls = "";
        Element typeWallsElement = new BlockWithParameters(
                getElements().select("li.item-params-list-item"),
                "Тип дома"
        ).getParameter();
        if (typeWallsElement != null) {
            typeWalls = typeWallsElement.text()
                    .split(":")[1]
                    .trim();
            typeWalls = convertTextToNumber ? transformTypeWallsInNumber(typeWalls) : typeWalls;
        }
        content.add(typeWalls + ";");
        return content;
    }

    private String transformTypeWallsInNumber(String typeWalls) {
        List<String> namesTypeWalls = new ArrayList<String>(
                Arrays.asList(
                        "блочный",
                        "шлакоблочный",
                        "деревянный",
                        "монолитный",
                        "газобетонный",
                        "панельный",
                        "кирпичный")
        );
        if (namesTypeWalls.contains(typeWalls)) {
            return String.valueOf(
                    namesTypeWalls.indexOf(typeWalls) + 1
            );
        }
        return null;
    }
}
