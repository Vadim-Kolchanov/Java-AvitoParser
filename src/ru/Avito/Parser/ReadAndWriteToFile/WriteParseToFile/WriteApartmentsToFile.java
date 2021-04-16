package ru.Avito.Parser.ReadAndWriteToFile.WriteParseToFile;

import ru.Avito.Parser.MyException.AllPagesHaveBeenParsingException;
import ru.Avito.Parser.Parsing.ParsingApartment.ParseApartment;
import ru.Avito.Parser.Parsing.ParsingApartment.URLsNeedParsing;
import ru.Avito.Parser.ReadAndWriteToFile.Headers;
import ru.Avito.Parser.ReadAndWriteToFile.WriteReadFile;
import ru.Avito.Parser.actToCollection.ActToList;

import java.io.IOException;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class WriteApartmentsToFile implements Headers {

    private final int indexCity;
    private final WriteReadFile writeReadToFile;
    private final URLsNeedParsing urlsNeedParsing;

    public WriteApartmentsToFile(int indexCity, WriteReadFile writeReadToFile, URLsNeedParsing urlsNeedParsing) throws IOException {
        this.indexCity = indexCity;
        this.writeReadToFile = writeReadToFile;
        this.urlsNeedParsing = urlsNeedParsing;
        addHeaders();
    }

    @Override
    public void addHeaders() throws IOException {
        if (this.writeReadToFile.fileIsEmpty()) {
            this.writeReadToFile.write(
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
                            + "URL\n",
                    false
            );
        }
    }

    public void write() throws IOException {
        try{
            for (String url: urlsNeedParsing.getURLsForParsing()) {
                String contentParse = new ParseApartment(url)
                        .getContentParse();
                contentParse = indexCity + ";" + contentParse;
                contentParse += url + "\n";
                this.writeReadToFile.write(
                        contentParse,
                        true
                );
                Thread.sleep(3000);
            }
            throw new AllPagesHaveBeenParsingException();
        } catch (AllPagesHaveBeenParsingException ex) {
            System.out.println("\nParse Apartments is finished! For IndexCity: " + indexCity);
            System.out.println(ex.getMessage());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
