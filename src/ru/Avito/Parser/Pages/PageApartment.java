package ru.Avito.Parser.Pages;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.Avito.Parser.Connecting.ConnectToWebSite;
import ru.Avito.Parser.Connecting.RetryConnectToWebSite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class PageApartment implements Page {

    private final String urlPageApartment;
    private Document connect;

    public PageApartment(String urlPageApartment) {
        this.urlPageApartment = urlPageApartment;
    }

    @Override
    public Elements getElements() throws IOException {
        if (this.connect == null) {
            this.connect = new RetryConnectToWebSite(
                               new ConnectToWebSite(
                                    this.urlPageApartment
                               )
            ).getConnect();
        }
        return this.connect.getElementsByAttributeValue(
                "class",
                "item-view js-item-view "
        );
    }

    @Override
    public List<String> getContent() throws IOException {
        return new ArrayList<String>();
    }
}
