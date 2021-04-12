package ru.Avito.Parser.MyException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class AllPagesHaveBeenParsingException extends Exception {

    public AllPagesHaveBeenParsingException() {
        super("All pages have been successfully parsed. Don't worry :)");
    }


}
