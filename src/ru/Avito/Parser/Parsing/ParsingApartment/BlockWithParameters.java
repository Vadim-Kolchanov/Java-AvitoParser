package ru.Avito.Parser.Parsing.ParsingApartment;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class BlockWithParameters {

    private final Elements parameters;
    private final String nameParameters;

    public BlockWithParameters(Elements parameters, String nameParameters) {
        this.parameters = parameters;
        this.nameParameters = nameParameters;
    }

    public Element getParameter() {
        for (Element parameter: parameters) {
            if (nameParameters.equals(
                                    parameter.text()
                                    .split(":")[0]
                                    .trim()
                               )
            ) {
                return parameter;
            }
        }
        return null;
    }
}
