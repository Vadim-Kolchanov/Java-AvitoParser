package ru.Avito.Parser.actToCollection;

import java.util.List;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class ActToList {

    private final List<String> list;

    public ActToList(List<String> list) {
        this.list = list;
    }

    public String getStringOfList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line: this.list) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    public List<String> removeDuplicate(List<String> contentForRemove) {
        this.list.removeAll(
                contentForRemove
        );
        return this.list;
    }
}
