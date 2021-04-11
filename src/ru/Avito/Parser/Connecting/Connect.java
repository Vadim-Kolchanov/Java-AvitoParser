package ru.Avito.Parser.Connecting;

import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public interface Connect {

    public Document getConnect() throws IOException;
}
