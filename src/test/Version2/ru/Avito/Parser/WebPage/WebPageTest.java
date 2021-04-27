package Version2.ru.Avito.Parser.WebPage;

import org.junit.jupiter.api.Test;

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

    /**
     * @TODO
     */
}