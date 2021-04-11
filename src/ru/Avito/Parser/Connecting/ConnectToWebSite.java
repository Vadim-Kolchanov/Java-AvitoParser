package ru.Avito.Parser.Connecting;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class ConnectToWebSite implements Connect {

    private final String url;

    public ConnectToWebSite(final String url) {
        this.url = url;
    }

    @Override
    public Document getConnect() throws IOException {
        return Jsoup.connect(url).get();
    }
}
