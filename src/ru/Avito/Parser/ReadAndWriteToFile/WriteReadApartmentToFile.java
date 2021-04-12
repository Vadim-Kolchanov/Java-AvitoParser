package ru.Avito.Parser.ReadAndWriteToFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class WriteReadApartmentToFile implements WriteReadFile {

    private final WriteReadFile original;
    private final Path file;

    public WriteReadApartmentToFile(WriteReadFile original) throws IOException {
        this.original = original;
        this.file = fileInit();
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
                     + "URL\n"
            );
        }
        return file;
    }

    @Override
    public InputStream read() throws IOException {
        return this.original.read();
    }

    @Override
    public void write() throws IOException {
        this.original.write();
    }
}
