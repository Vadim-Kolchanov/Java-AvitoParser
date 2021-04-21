package Version2.ru.Avito.Parser.WebPage;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public interface JsoupWeb {

    Connection connect();

    Document parsedHTML() throws IOException;

    final class Fake implements JsoupWeb {
        private final JsoupWeb origin;
        private final String PATHNAME = "D:\\Java\\JavaParserAvito\\src\\Version2\\MaterialsForFakeClass\\ForJsoupWeb\\avitoHTML-perm-kvartiry-prodam.html";

        public Fake(final JsoupWeb origin) {
            this.origin = origin;
        }

        @Override
        public Connection connect() {
            return this.origin.connect();
        }

        @Override
        public Document parsedHTML() throws IOException {
            return Jsoup.parse(
                    new File(
                            PATHNAME
                    ),
                    Charset.defaultCharset().name()
            );
        }
    }
}
