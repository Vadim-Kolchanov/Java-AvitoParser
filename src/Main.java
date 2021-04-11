import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;
import ru.Avito.Parser.Connecting.ConnectCityToMorePages;
import ru.Avito.Parser.Pages.PageWithApartments;
import ru.Avito.Parser.ReadAndWriteToFile.WriteReadToFile;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class Main {

    public static void main(String[] args) throws Exception {
        NameOfCitiesAndURLs city = NameOfCitiesAndURLs.PERM;
        System.out.println(city.name() + " " + city.getURL());

        new WriteReadToFile(
                "D:\\Java\\JavaParserAvito\\src\\DataURLsCity",
                "URLs",
                new PageWithApartments(
                        new ConnectCityToMorePages(
                                city
                        )
                )
        ).write();
   }

}
