package Version2.ru.Avito.Parser.WebPage;

import Version2.ru.Avito.Parser.Exceptions.HtmlNotParsedException;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Kolchanov Vadim
 */
class WebPageTest {

    @Test()
    public void testForNullParsedHTML() {
        WebPage webPage = new WebPage(null);
        assertThrows(IllegalArgumentException.class, () -> webPage.parsedHTML());
    }
}