package Version2.ru.Avito.Parser.WebPage;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public interface JsoupWeb {

    Connection connect();

    Document parsedHTML() throws IOException;
}
