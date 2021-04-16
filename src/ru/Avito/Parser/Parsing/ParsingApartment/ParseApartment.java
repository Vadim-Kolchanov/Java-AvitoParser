package ru.Avito.Parser.Parsing.ParsingApartment;

import ru.Avito.Parser.Pages.PageApartment;
import ru.Avito.Parser.Parsing.Parse;
import ru.Avito.Parser.Parsing.ParsingApartment.ParametersApartment.*;
import ru.Avito.Parser.actToCollection.ActToList;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class ParseApartment implements Parse {

    private final String url;

    public ParseApartment(String url) {
        this.url = url;
    }

    @Override
    public String getContentParse() throws IOException {
        return new ActToList(
                   new PriceApartment(
                       new Year(
                           new Season(
                               false,
                               new KitchenArea(
                                   new LivingSpace(
                                       new TotalArea(
                                           new NumberOfFloors(
                                               new Floor(
                                                   new NumberOfRooms(
                                                        new TypeWalls(
                                                            false,
                                                            new CoordinatesApartment(
                                                                new PageApartment(url)
                                                            )
                                                        )
                                                   )
                                               )
                                           )
                                       )
                                   )
                               )
                           )
                       )
                   ).getContent()
        ).getStringOfList();
    }
}
