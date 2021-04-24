package Version2.ru.Avito.Parser;

/**
 * @Author Kolchanov Vadim
 *
 * Класс служит для обработки исключений статического метода Thread.sleep,
 * чтобы каждый раз, где мы его применяем, не обрабатывать исключения,
 * можно воспользоваться этим классом и методом sleep()
 */
public final class HandledThreadSleep {

    private final long millis;

    public HandledThreadSleep(long millis) {
        this.millis = millis;
    }

    /**
     * Метод вызывает статический метод Thread.sleep.
     * Ловит исключение и пишет в консоль весь стек-трейс
     */
    public void sleep() {
        try {
            Thread.sleep(this.millis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
