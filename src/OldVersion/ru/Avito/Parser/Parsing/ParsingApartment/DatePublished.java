package OldVersion.ru.Avito.Parser.Parsing.ParsingApartment;

import org.jsoup.select.Elements;

import java.util.*;

/**
 * @Project JavaParserAvito
 * @Author Kolchanov Vadim
 */
public class DatePublished {

   private final Elements datePublishedElements;

    public DatePublished(Elements datePublishedElements) {
        this.datePublishedElements = datePublishedElements;
    }

    public String[] getDatePublished() {
        Elements datePublishedElement = this.datePublishedElements
                .select("div.title-info-metadata-item-redesign");
        if (datePublishedElement != null) {
            return getDayMonthYear(
                    datePublishedElement.text()
                    .split(" ")
            );
        }
        return null;
    }

    public String[] getDayMonthYear(String[] datePublished) {
        Calendar calendar = new GregorianCalendar();
        String[] dayMonthYear = new String[3];
        switch (datePublished.length) {
            case 4 -> {
                dayMonthYear[0] = datePublished[0];
                dayMonthYear[1] = String.valueOf(
                        getMonthsNumbers()
                                .get(datePublished[1])
                );
                dayMonthYear[2] = getYear(datePublished[1], calendar);
                return dayMonthYear;
            }
            case 3 -> {
                switch (datePublished[0]) {
                    case "сегодня" -> {
                        return getDayMonthYearForToday(calendar);
                    }
                    case "вчера" -> {
                        calendar.add(Calendar.DATE, -1);
                        return getDayMonthYearForToday(calendar);
                    }
                }
            }
        }
        return null;
    }

    public String[] getDayMonthYearForToday(Calendar calendar) {
        String[] dayMonthYear = new String[3];
        dayMonthYear[0] = String.valueOf(
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        dayMonthYear[1] = String.valueOf(
                calendar.get(Calendar.MONTH) + 1
        );
        dayMonthYear[2] = String.valueOf(
                calendar.get(Calendar.YEAR)
        );
        return dayMonthYear;
    }

    public String getYear(String month, Calendar calendar) {
        int monthNow = calendar.get(Calendar.MONTH) + 1;
        int yearNow = calendar.get(Calendar.YEAR);
        if (getMonthsNumbers().get(month) == 12 && monthNow == 1) {
            return String.valueOf(
                    yearNow - 1
            );
        }
        return String.valueOf(
                yearNow
        );
    }

    public Map<String, Integer> getMonthsNumbers() {
        Map<String, Integer> monthsNumbers = new HashMap<>();
        String[] namesMonths = new String[]{
                "января",
                "февраля",
                "марта",
                "апреля",
                "мая",
                "июня",
                "июля",
                "августа",
                "сентября",
                "октября",
                "ноября",
                "декабря"
        };
        for (int i = 0; i < namesMonths.length; i++) {
            monthsNumbers.put(namesMonths[i], i + 1);
        }
        return monthsNumbers;
    }
}
