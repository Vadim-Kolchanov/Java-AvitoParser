package Version2.ru.Avito.Parser.WebPage;

import org.jsoup.nodes.Document;

/**
 * @Author Kolchanov Vadim
 *
 * Класс - декоратор, кэширует результаты методов
 */
public final class CachedWebPage implements JsoupWeb {

    private final JsoupWeb webPage;
    private Document parsedHTML;

    public CachedWebPage(JsoupWeb webPage) {
        this.webPage = webPage;
    }

    /**
     * @return преобразованную html страницу
     * @throws Exception
     */
    @Override
    public Document parsedHTML() throws Exception {
        if (this.parsedHTML == null) {
            this.parsedHTML = webPage.parsedHTML();
        }

        return this.parsedHTML;
    }
}
