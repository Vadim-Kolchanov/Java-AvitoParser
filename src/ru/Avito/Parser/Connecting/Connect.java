package ru.Avito.Parser.Connecting;

import org.jsoup.nodes.Document;
import ru.Avito.Parser.Cities.City;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public interface Connect extends City {

    public String getURL();

    public Document getConnect() throws IOException;

}
