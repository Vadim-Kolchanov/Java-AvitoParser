package ru.Avito.Parser.Pages;

import org.jsoup.select.Elements;
import ru.Avito.Parser.MyException.AllPagesHaveBeenParsingException;

import java.io.IOException;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public interface Page {

    Elements getElements() throws IOException, AllPagesHaveBeenParsingException;

    List<String> getContent() throws IOException, AllPagesHaveBeenParsingException;

}
