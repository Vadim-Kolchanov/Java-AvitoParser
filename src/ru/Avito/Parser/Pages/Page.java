package ru.Avito.Parser.Pages;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public interface Page {

    Elements getElements() throws IOException;

    List<String> getContent() throws IOException;

}
