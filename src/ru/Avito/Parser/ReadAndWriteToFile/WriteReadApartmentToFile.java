package ru.Avito.Parser.ReadAndWriteToFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class WriteReadApartmentToFile implements WriteReadFile {

    private final WriteReadFile original;

    public WriteReadApartmentToFile(WriteReadFile original) {
        this.original = original;
    }

    @Override
    public Path fileInit() throws IOException {
        Path file = original.fileInit();
        if (Files.size(file) != 0) return file;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile()))) {
            writer.write(
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
                     + "URL"
            );
        }
        return file;
    }

    @Override
    public void write() throws IOException {
        this.original.write();
    }
}
