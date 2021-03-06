package OldVersion.ru.Avito.Parser.ReadAndWriteToFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public interface WriteReadFile {

    boolean fileIsEmpty() throws IOException;

    void write(String content, boolean append) throws IOException;

    InputStream read() throws IOException;

}
