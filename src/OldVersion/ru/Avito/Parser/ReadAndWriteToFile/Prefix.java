package OldVersion.ru.Avito.Parser.ReadAndWriteToFile;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public enum Prefix {
    URLS ("URLs"),
    APARTMENT ("Apartment");

    private final String prefix;

    Prefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return this.prefix;
    }
}
