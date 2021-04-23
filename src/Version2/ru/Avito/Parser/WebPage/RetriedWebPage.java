package Version2.ru.Avito.Parser.WebPage;

import Version2.ru.Avito.Parser.SafeThreadSleep;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class RetriedWebPage implements JsoupWeb {

    private final JsoupWeb webPage;
    private final int attempts;

    public RetriedWebPage(String url) {
        this(new WebPage(url));
    }

    public RetriedWebPage(JsoupWeb webPage) {
        this(webPage, 5);
    }

    private RetriedWebPage(JsoupWeb webPage, int attempts) {
        this.webPage = webPage;
        this.attempts = attempts;
    }

    @Override
    public Document parsedHTML() throws IOException {
        try {
            return webPage.parsedHTML();
        } catch (IOException ex) {
            if (attempts < 2) {
                throw new IOException("Error in getting parsedHTML", ex);
            }
            System.out.println("Error! Retrying to get parsed HTML. Attempts left: " + this.attempts);
            new SafeThreadSleep(5000).sleep();
            return new RetriedWebPage(this.webPage, this.attempts - 1)
                    .parsedHTML();
        }
    }
}
