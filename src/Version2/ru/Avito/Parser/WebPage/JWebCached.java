package Version2.ru.Avito.Parser.WebPage;

import Version2.ru.Avito.Parser.Exceptions.HtmlNotParsedException;
import org.jsoup.nodes.Document;

/**
 * @Author Kolchanov Vadim
 *
 * Класс - декоратор, кэширует результаты методов
 */
public final class JWebCached implements JsoupWeb {

    private final JsoupWeb webPage;
    private Document CachedParsedHTML;

    public JWebCached(JsoupWeb webPage) {
        this.webPage = webPage;
    }

    /**
     * @return преобразованную html страницу
     * @throws HtmlNotParsedException когда метод не смог распарсить html
     */
    @Override
    public Document parsedHTML() throws HtmlNotParsedException {
        if (this.CachedParsedHTML == null) {
            this.CachedParsedHTML = webPage.parsedHTML();
        }

        return this.CachedParsedHTML;
    }
}
