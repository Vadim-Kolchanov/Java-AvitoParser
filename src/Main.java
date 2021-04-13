import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;
import ru.Avito.Parser.ContentFile.ContentOfFile;
import ru.Avito.Parser.ContentFile.StorageContentOfFile;
import ru.Avito.Parser.Pages.PageWithApartments;
import ru.Avito.Parser.Parsing.ParsingApartment.URLsNeedParsing;
import ru.Avito.Parser.ReadAndWriteToFile.Prefix;
import ru.Avito.Parser.ReadAndWriteToFile.WriteParseToFile.WriteApartmentsToFile;
import ru.Avito.Parser.ReadAndWriteToFile.WriteReadFile;
import ru.Avito.Parser.ReadAndWriteToFile.WriteReadToFile;
import ru.Avito.Parser.ReadAndWriteToFile.WriteParseToFile.WriteURLsToFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class Main {

    public static void main(String[] args) throws Exception {
        NameOfCitiesAndURLs city = NameOfCitiesAndURLs.PERM;
        System.out.println(city.name() + " " + city.getURL());

        Main start = new Main();
        boolean isFinished = false;
        do {
            System.out.println(
                    "\n"
                            + "Введите 1, чтобы запарсить ссылки на квартиры\n"
                            + "Введите 2, чтобы запарсить параметры квартир\n"
                            + "Введите 0, чтобы выйти"
            );
            switch (start.getNumber()) {
                case 0 -> isFinished = true;
                case 1 -> start.parseURlsApartments(
                                city,
                               "D:\\Java\\JavaParserAvito\\src\\DataURLsCity"
                          );
                case 2 -> start.parseParametersApartments(
                                1,
                                city,
                               "D:\\Java\\JavaParserAvito\\src\\DataParsedPageCity",
                               "D:\\Java\\JavaParserAvito\\src\\DataURLsCity"

                          );
                default -> {
                    System.out.println("Введите другое число");
                }
            }
        } while (!isFinished);
    }

    public void parseURlsApartments(NameOfCitiesAndURLs city, String pathToFolderWithUrls) throws Exception {
        WriteReadFile writeReadToFileWithURLs = new WriteReadToFile(
                pathToFolderWithUrls,
                Prefix.URLS,
                city
        );
        new WriteURLsToFile(
            writeReadToFileWithURLs,
                1,
                city
        ).write();
    }

    public void parseParametersApartments(int indexCity,
                                          NameOfCitiesAndURLs city,
                                          String pathToFolderWithApartments,
                                          String pathToFolderWithUrls
    ) throws Exception {
        WriteReadFile writeReadToFileWithApartments = new WriteReadToFile(
                                                                pathToFolderWithApartments,
                                                                Prefix.APARTMENT,
                                                                city
        );
        WriteReadFile writeReadToFileWithURLs = new WriteReadToFile(
                                                         pathToFolderWithUrls,
                                                         Prefix.URLS,
                                                         city
        );
        new WriteApartmentsToFile(
            indexCity,
            writeReadToFileWithApartments,
            new URLsNeedParsing(
                new StorageContentOfFile(
                    new ContentOfFile(
                            writeReadToFileWithURLs
                    )
                ),
                new StorageContentOfFile(
                    new ContentOfFile(
                              writeReadToFileWithApartments
                    )
                )
            )
        ).write();
    }

   public int getNumber() {
        while (true) {
            try{
                BufferedReader reader = new BufferedReader(
                                            new InputStreamReader(
                                                    System.in
                                            )
                                        );
                System.out.print("Введите число: ");
                return Integer.parseInt(
                        reader.readLine()
                       );
            } catch (NumberFormatException ex) {
                System.out.println("\nВы ввели не число! Повторите попытку.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
   }

}
