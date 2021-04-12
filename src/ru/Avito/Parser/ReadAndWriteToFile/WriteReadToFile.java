package ru.Avito.Parser.ReadAndWriteToFile;

import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;

import java.io.*;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class WriteReadToFile implements WriteReadFile {

    private final File file;
    private final String pathToFolder;
    private final Prefix prefix;
    private final NameOfCitiesAndURLs city;

    public WriteReadToFile(String pathToFolder, Prefix prefix, NameOfCitiesAndURLs city) throws IOException {
        this.pathToFolder = pathToFolder;
        this.prefix = prefix;
        this.city = city;
        this.file = fileInit();
    }

    private File fileInit() {
        String pathToFile = pathToFolder + "\\" + prefix.name() + "-" + city.name() + ".csv";
        return new File(pathToFile);
    }

    @Override
    public boolean fileIsEmpty() {
        return file.length() == 0;
    }

    @Override
    public InputStream read() throws IOException {
        return new FileInputStream(file);
    }

    @Override
    public void write(String content, boolean append) throws IOException {
      try (BufferedWriter writer = new BufferedWriter(
                                       new FileWriter(
                                               file,
                                               append
                                       )
                                   )
        ) {
          writer.write(content);
          writer.flush();
        }
    }
}
