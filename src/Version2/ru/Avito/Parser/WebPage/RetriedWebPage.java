package Version2.ru.Avito.Parser.WebPage;

import Version2.ru.Avito.Parser.Exceptions.HtmlNotParsedException;
import Version2.ru.Avito.Parser.HandledThreadSleep;
import org.jsoup.nodes.Document;

/**
 * @Author Kolchanov Vadim
 *
 * Класс - декоратор, в случае выброса исключений в методах,
 * повторяет их вызов через 5 секунд, пока не кончатся попытки
 */
public final class RetriedWebPage implements JsoupWeb {

    private final static long TIME_WAITING = 5000L;
    private final static int INITIAL_NUMBERS_OF_ATTEMPTS = 5;

    private final JsoupWeb webPage;
    private final int attempts;
    private final HandledThreadSleep threadSleep;

    public RetriedWebPage(final String url) {
        this(new WebPage(url));
    }

    public RetriedWebPage(final JsoupWeb webPage) {
        this(
                webPage,
                INITIAL_NUMBERS_OF_ATTEMPTS,
                new HandledThreadSleep(TIME_WAITING)
        );
    }

    private RetriedWebPage(final JsoupWeb webPage,
                           final int attempts,
                           final HandledThreadSleep threadSleep) {
        this.webPage = webPage;
        this.attempts = attempts;
        this.threadSleep = threadSleep;
    }

    /**
     * @return преобразованную html страницу
     * @throws HtmlNotParsedException когда метод не смог распарсить html
     */
    @Override
    public Document parsedHTML() throws HtmlNotParsedException {
        try {
            return webPage.parsedHTML();
        } catch (HtmlNotParsedException ex) {
            if (attempts < 1) {
                throw new HtmlNotParsedException("Error in getting parsedHTML", ex);
            }
            System.out.println("Error! Retrying to get parsed HTML. Attempts left: " + this.attempts);
            threadSleep.sleep();

            return new RetriedWebPage(this.webPage, this.attempts - 1, this.threadSleep)
                    .parsedHTML();
        }
    }
}
