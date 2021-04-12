package ru.Avito.Parser.ContentFile;

import java.io.IOException;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class StorageContentOfFile implements ContentFile {

    private final ContentFile contentFile;
    private List<String> content;

    public StorageContentOfFile(ContentFile contentFile) {
        this.contentFile = contentFile;
    }

    @Override
    public List<String> getContent() throws IOException {
        if (content.isEmpty()) {
            this.content = contentFile.getContent();
        }
        return content;
    }
}
