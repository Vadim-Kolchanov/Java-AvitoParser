package Version2.ru.Avito.Parser.WebPage;

import Version2.ru.Avito.Parser.Exceptions.HtmlNotParsedException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @Author Kolchanov Vadim
 *
 * Класс, содержит логику по работе с веб-страницой
 */
public final class WebPage implements JsoupWeb {

    private final String url;

    public WebPage(final String url) {
        this.url = url;
    }

    private Connection connect() {
        if (url == null || url.equals("")) {
            throw new IllegalArgumentException("Error! Incorrect link!");
        }

        return Jsoup.connect(url);
    }

    /**
     * Метод возрващает преобразованную html страницу
     * @return преобразованную html страницу
     * @throws HtmlNotParsedException когда метод не смог распарсить html
     */
    @Override
    public Document parsedHTML() throws HtmlNotParsedException {
        try {
            return connect().get();
        } catch (IOException ex) {
            throw new HtmlNotParsedException(
                    String.format("Error in parsing html. URL to html: %s", url),
                    ex);
        }

    }
}
