package Version2.ru.Avito.Parser;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class SafeThreadSleep {

    private final long millis;

    public SafeThreadSleep(long millis) {
        this.millis = millis;
    }

    public void sleep() {
        try {
            Thread.sleep(this.millis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
