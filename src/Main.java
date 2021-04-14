import Multithreading.MyThreadsForApartments;
import Multithreading.MyThreadsForURLs;
import StartParse.StartParseParametersApartment;
import StartParse.StartParseURLs;
import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class Main {

    private Map<Integer, NameOfCitiesAndURLs> indexesAndCities;
    private final String pathToFolderWithUrls = "D:\\Java\\JavaParserAvito\\src\\DataParsedPageCity";
    private final String pathToFolderWithApartments = "D:\\Java\\JavaParserAvito\\src\\DataParsedApartments";

    private Map<Integer, NameOfCitiesAndURLs> getIndexesAndCities() {
        if (this.indexesAndCities != null) {
            return this.indexesAndCities;
        }
        Map<Integer, NameOfCitiesAndURLs> map = new HashMap<>();
        int index = 1;
        for (NameOfCitiesAndURLs city: NameOfCitiesAndURLs.values()) {
            map.put(index, city);
            index++;
        }
        this.indexesAndCities = map;
        return map;
    }

    public static void main(String[] args) throws Exception {
        NameOfCitiesAndURLs city = NameOfCitiesAndURLs.PERM;
        System.out.println(city.name() + " " + city.getURL());

        Main solutionMain = new Main();
        boolean isFinished = false;
        do {
            System.out.println(
                    "\n"
                            + "Введите 1, чтобы запарсить ссылки на квартиры\n"
                            + "Введите 2, чтобы запарсить параметры квартир\n"
                            + "Введите 3, чтобы в параллельном режиме запарсить ссылки на квартиры\n"
                            + "Введите 4, чтобы в параллельном режиме запарсить параметры квартир\n"
                            + "Введите 0, чтобы выйти"
            );
            switch (solutionMain.getNumber()) {
                case 0 -> isFinished = true;
                case 1 -> new StartParseURLs(
                                city,
                               solutionMain.pathToFolderWithUrls
                          ).startParsing();
                case 2 -> new StartParseParametersApartment(
                                1,
                                city,
                               solutionMain.pathToFolderWithUrls,
                               solutionMain.pathToFolderWithApartments
                          ).startParsing();
                case 3 -> new MyThreadsForURLs(
                                solutionMain.getIndexesAndCities(),
                                solutionMain.pathToFolderWithUrls
                          ).startThreads();
                case 4 -> new MyThreadsForApartments(
                                solutionMain.getIndexesAndCities(),
                                solutionMain.pathToFolderWithUrls,
                                solutionMain.pathToFolderWithApartments
                          ).startThreads();
                default -> System.out.println("Введите другое число");
            }
            //adding join() for main Thread
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
