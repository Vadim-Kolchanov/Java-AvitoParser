package OldVersion.ru.Avito.Parser.ContentFile;

import OldVersion.ru.Avito.Parser.ReadAndWriteToFile.WriteReadFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class ContentOfFile implements ContentFile {

    private final WriteReadFile writeReadFile;

    public ContentOfFile(WriteReadFile writeReadFile) {
        this.writeReadFile = writeReadFile;
    }

    @Override
    public List<String> getContent() throws IOException {
        List<String> content = new ArrayList<String>();
        try(BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(
                                                writeReadFile.read()
                                        )
                                    )
        ) {
            while (reader.ready()) {
                content.add(reader.readLine() + "\n");
            }
        } catch (FileNotFoundException ignored) {}
        return content;
    }
}
