package Version2.ru.Avito.Parser.WebPage;

import Version2.ru.Avito.Parser.HandledThreadSleep;
import org.jsoup.nodes.Document;

import java.io.IOException;

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

    public RetriedWebPage(String url) {
        this(new WebPage(url));
    }

    public RetriedWebPage(JsoupWeb webPage) {
        this(
                webPage,
                INITIAL_NUMBERS_OF_ATTEMPTS,
                new HandledThreadSleep(TIME_WAITING)
        );
    }

    private RetriedWebPage(JsoupWeb webPage, int attempts, HandledThreadSleep threadSleep) {
        this.webPage = webPage;
        this.attempts = attempts;
        this.threadSleep = threadSleep;
    }

    /**
     * @return преобразованную html страницу
     * @throws Exception
     */
    @Override
    public Document parsedHTML() throws Exception {
        try {
            return webPage.parsedHTML();
        } catch (IOException ex) {
            if (attempts < 1) {
                throw new Exception("Error in getting parsedHTML", ex);
            }
            System.out.println("Error! Retrying to get parsed HTML. Attempts left: " + this.attempts);
            threadSleep.sleep();

            return new RetriedWebPage(this.webPage, this.attempts - 1, this.threadSleep)
                    .parsedHTML();
        }
    }
}
