import StartParse.ParseParametersApartment;
import StartParse.ParseURLs;
import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;

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

        boolean isFinished = false;
        do {
            System.out.println(
                    "\n"
                            + "Введите 1, чтобы запарсить ссылки на квартиры\n"
                            + "Введите 2, чтобы запарсить параметры квартир\n"
                            + "Введите 0, чтобы выйти"
            );
            switch (new Main().getNumber()) {
                case 0 -> isFinished = true;
                case 1 -> new ParseURLs(
                                city,
                               "D:\\Java\\JavaParserAvito\\src\\DataURLsCity"
                          ).startParse();
                case 2 -> new ParseParametersApartment(
                                1,
                                city,
                               "D:\\Java\\JavaParserAvito\\src\\DataParsedPageCity",
                               "D:\\Java\\JavaParserAvito\\src\\DataURLsCity"
                          ).startParse();
                default -> {
                    System.out.println("Введите другое число");
                }
            }
        } while (!isFinished);
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
