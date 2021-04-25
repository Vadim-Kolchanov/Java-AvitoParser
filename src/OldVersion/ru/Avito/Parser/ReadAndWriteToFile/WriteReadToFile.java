package OldVersion.ru.Avito.Parser.ReadAndWriteToFile;

import OldVersion.ru.Avito.Parser.Cities.NameOfCitiesAndURLs;

import java.io.*;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class WriteReadToFile implements WriteReadFile {

    private final String pathToFolder;
    private final Prefix prefix;
    private final NameOfCitiesAndURLs city;

    public WriteReadToFile(String pathToFolder, Prefix prefix, NameOfCitiesAndURLs city) {
        this.pathToFolder = pathToFolder;
        this.prefix = prefix;
        this.city = city;
    }

    private File fileInit() {
        String pathToFile = pathToFolder + "\\" + prefix + "-" + city.name() + ".csv";
        return new File(pathToFile);
    }

    @Override
    public boolean fileIsEmpty() {
        return fileInit().length() == 0;
    }

    @Override
    public InputStream read() throws IOException {
        return new FileInputStream(fileInit());
    }

    @Override
    public void write(String content, boolean append) throws IOException {
      try (BufferedWriter writer = new BufferedWriter(
                                       new FileWriter(
                                               fileInit(),
                                               append
                                       )
                                   )
      ) {
          writer.write(content);
          writer.flush();
      } catch (FileNotFoundException ex) {
          System.out.println(ex.getMessage());
          System.out.println("Такой папки не существует! Измените путь к директории!");
          System.exit(-1);
      }
    }
}
