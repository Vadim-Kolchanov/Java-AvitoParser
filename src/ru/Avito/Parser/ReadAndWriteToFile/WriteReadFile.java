package ru.Avito.Parser.ReadAndWriteToFile;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public interface WriteReadFile {

    public Path fileInit() throws IOException;

    public void write() throws IOException;

}