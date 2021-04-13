package ru.Avito.Parser.Parsing;

import ru.Avito.Parser.MyException.AllPagesHaveBeenParsingException;

import java.io.IOException;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public interface Parse {

    String getContentParse() throws IOException;
}
