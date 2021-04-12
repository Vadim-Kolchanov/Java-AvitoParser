import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;
import ru.Avito.Parser.Connecting.ConnectCityToMorePages;
import ru.Avito.Parser.Connecting.ConnectCityToPage;
import ru.Avito.Parser.Pages.PageWithApartments;
import ru.Avito.Parser.ReadAndWriteToFile.WriteApartmentToFile;
import ru.Avito.Parser.ReadAndWriteToFile.WriteReadToFile;
import ru.Avito.Parser.ReadAndWriteToFile.WriteURLsToFile;

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
        boolean isFinished = true;
        do {
            System.out.println(
                    "\n"
                            + "Введите 1, чтобы запарсить ссылки на квартиры\n"
                            + "Введите 2, чтобы запарсить параметры квартир\n"
                            + "Введите 0, чтобы выйти"
            );
            switch (start.getNumber()) {
                case 0 -> System.exit(1);
                case 1 -> start.parseURlsApartments(city);
                case 2 -> start.parseParametersApartments(city);
                default -> {
                    System.out.println("Введите другое число");
                    isFinished = false;
                }
            }
        } while (!isFinished);
    }

    public void parseURlsApartments(NameOfCitiesAndURLs city) throws Exception {
        new WriteURLsToFile(
            new WriteReadToFile(
                        "D:\\Java\\JavaParserAvito\\src\\DataURLsCity",
                        "URLs",
                        city
            ),
            new PageWithApartments(
                new ConnectCityToMorePages(
                                        1,
                                        new ConnectCityToPage(
                                                city
                                        )
                )
            )
        ).write();
    }

    public void parseParametersApartments(NameOfCitiesAndURLs city) throws Exception {
        // В разработке...
        System.exit(0);

        new WriteApartmentToFile(
            new WriteReadToFile(
                        "D:\\Java\\JavaParserAvito\\src\\DataURLsCity",
                        "Apartment",
                        city
            ),
            new PageWithApartments(
                new ConnectCityToMorePages(
                                        1,
                                        new ConnectCityToPage(city)
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
