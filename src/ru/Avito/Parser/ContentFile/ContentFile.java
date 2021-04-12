package ru.Avito.Parser.ContentFile;

import java.io.IOException;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public interface ContentFile {

    List<String> getContent() throws IOException;
}
