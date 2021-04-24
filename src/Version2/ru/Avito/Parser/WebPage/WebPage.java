package Version2.ru.Avito.Parser.WebPage;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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
     * @throws Exception
     */
    @Override
    public Document parsedHTML() throws Exception {
        return connect().get();
    }
}
