package ru.Avito.Parser.Pages;

import org.jsoup.select.Elements;
import ru.Avito.Parser.MyException.AllPagesHaveBeenParsing;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public interface Page {

    public Elements getElements() throws IOException, AllPagesHaveBeenParsing;

    public StringBuilder getContent() throws IOException, AllPagesHaveBeenParsing;

    public String getNameCity();

}
