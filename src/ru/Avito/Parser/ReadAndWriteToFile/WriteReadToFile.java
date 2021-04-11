package ru.Avito.Parser.ReadAndWriteToFile;

import ru.Avito.Parser.MyException.AllPagesHaveBeenParsing;
import ru.Avito.Parser.Pages.Page;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class WriteReadToFile implements WriteReadFile {

    private final String pathToFolder;
    private final String prefix;
    private Page page;
    private final Path file;

    public WriteReadToFile(String pathToFolder, String prefix, Page page) throws IOException {
        this.pathToFolder = pathToFolder;
        this.prefix = prefix;
        this.page = page;
        this.file = fileInit();
    }

    public WriteReadToFile(String pathToFolder, Page page) throws IOException {
        this.pathToFolder = pathToFolder;
        this.prefix = "";
        this.page = page;
        this.file = fileInit();
    }

    @Override
    public Path fileInit() throws IOException {
        String pathToFile = pathToFolder + "\\" + prefix + "-" + page.getCity().name() + ".csv";
        Path path = Paths.get(pathToFile);
        if (Files.exists(path)) {
            return path;
        }
        return Files.createFile(path);
    }

    @Override
    public void write() throws IOException {
        try {
            while (true) {
                StringBuilder list = page.getContent();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile(), true))) {
                    writer.write(list.toString());
                    writer.flush();
                }
                Thread.sleep(3000);
            }
        } catch (AllPagesHaveBeenParsing ex) {
            System.out.println("Parser is finished!");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }


    }
}
