package Version2.ru.Avito.Parser.WebPage;

import Version2.ru.Avito.Parser.Exceptions.HtmlNotParsedException;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Kolchanov Vadim
 *
 * Класс - декоратор, не приведет к немедленному сбою при исключении,
 * а повторит попытку через {@link JWebRetry#TIME_WAITING} секунд,
 * пока попытки не достигнут максимума {@link JWebRetry#MAX_NUMBERS_OF_ATTEMPTS}
 */
public final class JWebRetry implements JsoupWeb {

    private final static long TIME_WAITING = 5000L;
    private final static int MAX_NUMBERS_OF_ATTEMPTS = 5;

    private final JsoupWeb webPage;

    public JWebRetry(final String url) {
        this(new WebPage(url));
    }

    public JWebRetry(final JsoupWeb webPage) {
        this.webPage = webPage;
    }

    /**
     * @return преобразованную html страницу
     * @throws HtmlNotParsedException когда метод не смог распарсить html
     */
    @Override
    public Document parsedHTML() throws HtmlNotParsedException {
        int attempts = 0;
        final List<Exception> failures = new ArrayList<>(MAX_NUMBERS_OF_ATTEMPTS);
        while (attempts < MAX_NUMBERS_OF_ATTEMPTS) {
            try {
                return webPage.parsedHTML();
            } catch (final HtmlNotParsedException ex) {
                ++attempts;
                failures.add(ex);
                this.sleep();
            }
        }
        throw new HtmlNotParsedException(
                String.format(
                        "failed after %d attempts: %s",
                        failures.size(),
                        this.mappedMessage(failures)
                ),
                failures.get(failures.size() - 1)
        );
    }

    private String mappedMessage(List<Exception> failures) {
        StringBuilder message = new StringBuilder();
        for (Exception ex: failures) {
            message.append(ex.getMessage()).append(";\n");
        }
        return message.toString();
    }

    private void sleep() {
        try {
            Thread.sleep(TIME_WAITING);
        } catch (final InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(
                    "Unexpected interruption while retrying getting parsed html",
                    ex
            );
        }
    }
}
