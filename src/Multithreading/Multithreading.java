package Multithreading;

import StartParse.StartParse;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class Multithreading implements Runnable {

    private final StartParse startParse;

    public Multithreading(StartParse startParse) {
        this.startParse = startParse;
    }

    @Override
    public void run() {
        try {
            startParse.startParsing();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
