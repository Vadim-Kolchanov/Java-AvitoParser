package ru.Avito.Parser.Pages;

import org.jsoup.select.Elements;
import ru.Avito.Parser.Cities.City;
import ru.Avito.Parser.MyException.AllPagesHaveBeenParsing;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public interface Page extends City {

    public Elements getElements() throws IOException, AllPagesHaveBeenParsing;

    public StringBuilder getContent() throws IOException, AllPagesHaveBeenParsing;

}
