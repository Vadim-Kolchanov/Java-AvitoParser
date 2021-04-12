package ru.Avito.Parser.ReadAndWriteToFile;

import ru.Avito.Parser.ContentFile.ContentOfFile;
import ru.Avito.Parser.ContentFile.StorageContentOfFile;
import ru.Avito.Parser.MyException.AllPagesHaveBeenParsingException;
import ru.Avito.Parser.Pages.Page;

import java.io.IOException;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class WriteURLsToFile {

    private final WriteReadFile writeReadToFile;
    private final Page page;
    private final List<String> haveURLsNow;

    public WriteURLsToFile(WriteReadFile writeReadToFile, Page page) throws IOException {
        this.writeReadToFile = writeReadToFile;
        this.page = page;
        this.haveURLsNow = new StorageContentOfFile(
                               new ContentOfFile(
                                    writeReadToFile
                               )
                           ).getContent();
    }

    public List<String> removeDuplicate(List<String> content) throws AllPagesHaveBeenParsingException {
        content.removeAll(
                haveURLsNow
        );
        if (content.isEmpty()) throw new AllPagesHaveBeenParsingException();
        return content;
    }

    public String getStringOfList(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line: list) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    public void write() throws IOException {
        try {
            while (true) {
                writeReadToFile.write(
                        getStringOfList(
                                removeDuplicate(
                                        page.getContent()
                                )
                        ),
                        true
                );
                Thread.sleep(3000);
            }
        } catch (AllPagesHaveBeenParsingException ex) {
            System.out.println("Parser is finished!");
            System.out.println(ex.getMessage());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
