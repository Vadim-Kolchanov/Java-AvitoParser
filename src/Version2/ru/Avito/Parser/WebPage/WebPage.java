package Version2.ru.Avito.Parser.WebPage;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class WebPage implements JsoupWeb {

    private final String url;

    public WebPage(final String url) {
        this.url = url;
    }

    @Override
    public Connection connect() {
        if (url == null || url.equals("")) {
            throw new IllegalArgumentException("Error! Incorrect link!");
        }
        return Jsoup.connect(url);
    }

    @Override
    public Document parsedHTML() throws IOException {
        return connect().get();
    }
}
