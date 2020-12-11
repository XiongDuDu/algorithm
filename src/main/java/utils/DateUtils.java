package utils;

import sun.util.resources.LocaleData;

import java.time.LocalDate;
import java.util.Locale;

/**
 * @author duwei7
 * @time 2020/12/10 11:38
 */
public class DateUtils {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2014, 3, 18);
        System.out.println(date);
        System.out.println(LocalDate.now());
        System.out.println(LocalDate.parse("2014-03-18"));;
    }
}
