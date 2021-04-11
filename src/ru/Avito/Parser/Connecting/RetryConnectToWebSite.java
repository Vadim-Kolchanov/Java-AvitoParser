package ru.Avito.Parser.Connecting;

import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class RetryConnectToWebSite implements Connect {

    private final Connect original;
    private int attempts = 5;

    public RetryConnectToWebSite(Connect original) {
        this.original = original;
    }

    @Override
    public Document getConnect() throws IOException {
        while (this.attempts != 0) {
            try {
                return original.getConnect();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Ooops! Error! Retrying connect. Attempts left: " + this.attempts);
                this.attempts--;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException interEx) {
                    interEx.printStackTrace();
                }
                return getConnect();
            }
        }
        throw new IOException();
    }
}
