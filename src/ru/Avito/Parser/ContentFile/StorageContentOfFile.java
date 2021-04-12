package ru.Avito.Parser.ContentFile;

import java.io.IOException;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class StorageContentOfFile implements ContentFile {

    private final ContentFile contentOfFile;
    private List<String> content;

    public StorageContentOfFile(ContentFile contentOfFile) {
        this.contentOfFile = contentOfFile;
    }

    @Override
    public List<String> getContent() throws IOException {
        if (content == null) {
            this.content = contentOfFile.getContent();
        }
        return content;
    }
}
