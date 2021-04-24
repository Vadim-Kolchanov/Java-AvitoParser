package Version2.ru.Avito.Parser.WebPage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.nio.charset.Charset;

/**
 * @Author Kolchanov Vadim
 */
public interface JsoupWeb {

    Document parsedHTML() throws Exception;
    
    /**
     * Фейковый класс служит для тестов вместо мокинга
     */
    final class Fake implements JsoupWeb {
        private final String PATHNAME = "D:\\Java\\JavaParserAvito\\src\\test\\resources\\materialsForFakeClass\\ForJsoupWeb\\avitoHTML-perm-kvartiry-prodam.html";

        /**
         * Метод парсит html страницу, которая находится файлом на ПК
         * @return преобразованную html 
         * @throws Exception
         */
        @Override
        public Document parsedHTML() throws Exception {
            return Jsoup.parse(
                    new File(
                            PATHNAME
                    ),
                    Charset.defaultCharset().name()
            );
        }
    }
}
