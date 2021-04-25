package Version2.ru.Avito.Parser.WebPage;

import Version2.ru.Avito.Parser.Exceptions.HtmlNotParsedException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Author Kolchanov Vadim
 */
public interface JsoupWeb {

    Document parsedHTML() throws HtmlNotParsedException;
    
    /**
     * Фейковый класс служит для тестов вместо мокинга
     */
    final class Fake implements JsoupWeb {
        private final String PATHNAME = "D:\\Java\\JavaParserAvito\\src\\test\\resources\\materialsForFakeClass\\ForJsoupWeb\\avitoHTML-perm-kvartiry-prodam.html";

        /**
         * Метод парсит html страницу, которая находится файлом на ПК
         * @return преобразованную html 
         * @throws HtmlNotParsedException когда метод не смог распарсить html
         */
        @Override
        public Document parsedHTML() throws HtmlNotParsedException {
            try {
                return Jsoup.parse(
                        new File(PATHNAME),
                        Charset.defaultCharset().name()
                );
            } catch (IOException ex) {
                throw new HtmlNotParsedException(
                        String.format("Error in parsing html file. Path to the file: %s", PATHNAME),
                        ex
                );
            }

        }
    }
}
