package ru.Avito.Parser.ReadAndWriteToFile;

import ru.Avito.Parser.MyException.AllPagesHaveBeenParsingException;
import ru.Avito.Parser.Pages.Page;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class WriteApartmentToFile implements Headers {

    private final WriteReadFile writeReadToFile;
    private final Page page;

    public WriteApartmentToFile(WriteReadFile writeReadToFile, Page page) throws IOException {
        this.writeReadToFile = writeReadToFile;
        this.page = page;
        addHeaders();
    }

    @Override
    public void addHeaders() throws IOException {
        if (this.writeReadToFile.fileIsEmpty()) {
            this.writeReadToFile.write(
                    "Индекс города;"
                            + "Широта;"
                            + "Долгота;"
                            + "Тип стен;"
                            + "Количество комнат;"
                            + "Этаж;"
                            + "Этажность;"
                            + "Общая площадь;"
                            + "Жилая площадь;"
                            + "Площадь кухни;"
                            + "Сезон;"
                            + "Год;"
                            + "Стоимость квартиры;"
                            + "URL\n",
                    false
            );
        }
    }

    public void write() throws IOException, AllPagesHaveBeenParsingException {
        this.writeReadToFile.write(
                page.getContent().toString(),
                true
        );
    }
}
