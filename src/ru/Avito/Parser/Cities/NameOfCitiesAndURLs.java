package ru.Avito.Parser.Cities;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public enum NameOfCitiesAndURLs {
    PERM ("https://www.avito.ru/perm/kvartiry/prodam-ASgBAgICAUSSA8YQ");

    private final String url;

    NameOfCitiesAndURLs(String url) {
        this.url = url;
    }

    public String getURL() {
        return this.url;
    }
}
