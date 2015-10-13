package ua.org.gostroy.jasper;

import net.sf.jasperreports.functions.standard.DateTimeFunctions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by s.panov on 10/1/2015.
 */
public class App {

    public static void main(String[] args) {

        DateTimeFunctions dateTimeFunctions = new DateTimeFunctions();
        String formatDate = dateTimeFunctions.DATEFORMAT(new Date(), "dd.MM.yyyy");
        System.out.println(formatDate);
        System.out.println();

        Locale locale = new Locale("uk", "UA");
//        Locale locale = new Locale("ru");
//        Locale locale = new Locale.Builder().setLanguage("ru").setScript("Cyrl").build();
//        String formatDate2 = dateTimeFunctions.DATEFORMAT(new Date(), "dd MMMM yyyy");
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy", locale);
        String formatDate2 = df.format(new Date());
        System.out.println("Тестируем русский ....");
        System.out.println(formatDate2);
        System.out.println();


        Locale locale3 = new Locale("uk", "UA");
        String formatDate3 = dateTimeFunctions.DATEFORMAT(new Date(), "dd MMMM yyyy");
        System.out.println(formatDate3);
    }
}
