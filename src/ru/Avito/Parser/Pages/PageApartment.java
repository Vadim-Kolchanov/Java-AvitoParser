package ru.Avito.Parser.Pages;

import org.jsoup.select.Elements;
import ru.Avito.Parser.Cities.NameOfCitiesAndURLs;
import ru.Avito.Parser.Connecting.Connect;
import ru.Avito.Parser.MyException.AllPagesHaveBeenParsing;
import ru.Avito.Parser.ReadAndWriteToFile.WriteReadFile;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class PageApartment implements Page {

    private final Connect connect;
    private final NameOfCitiesAndURLs city;
    private final WriteReadFile readFile;

    public PageApartment(NameOfCitiesAndURLs city, Connect connect, WriteReadFile readFile) {
        this.city = city;
        this.connect = connect;
        this.readFile = readFile;
    }

    @Override
    public Elements getElements() throws IOException, AllPagesHaveBeenParsing {
        return null;
    }

    @Override
    public StringBuilder getContent() throws IOException, AllPagesHaveBeenParsing {
        return null;
    }

    @Override
    public String getNameCity() {
        return city.name();
    }
}
