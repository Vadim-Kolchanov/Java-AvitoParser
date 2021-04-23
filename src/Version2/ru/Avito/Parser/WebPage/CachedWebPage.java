package Version2.ru.Avito.Parser.WebPage;

import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public final class CachedWebPage implements JsoupWeb {

    private final JsoupWeb webPage;
    private Document parsedHTML;

    public CachedWebPage(JsoupWeb webPage) {
        this.webPage = webPage;
    }

    @Override
    public Document parsedHTML() throws IOException {
        if (this.parsedHTML == null) {
            this.parsedHTML = webPage.parsedHTML();
        }
        return this.parsedHTML;
    }
}
