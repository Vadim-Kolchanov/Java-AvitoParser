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
    private String messageException = "Error in RetryConnectToWebSite";

    public RetryConnectToWebSite(Connect original) {
        this.original = original;
    }

    @Override
    public Document getConnect() throws IOException {
        while (this.attempts != 0) {
            try {
                return original.getConnect();
            } catch (IOException ex) {
                this.messageException = ex.getMessage();
                System.out.println(this.messageException);
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
        throw new IOException(this.messageException);
    }
}
