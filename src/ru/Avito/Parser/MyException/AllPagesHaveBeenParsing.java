package ru.Avito.Parser.MyException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class AllPagesHaveBeenParsing extends Exception {

    public AllPagesHaveBeenParsing() {
        super("All pages have been successfully parsed. Don't worry :)");
    }


}
