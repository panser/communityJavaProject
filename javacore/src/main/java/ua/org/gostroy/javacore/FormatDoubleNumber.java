package ua.org.gostroy.javacore;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by s.panov on 10/6/2015.
 */
public class FormatDoubleNumber {

    public static void main(String[] args) {
        String formatDouble = new DecimalFormat("0").format(Double.valueOf(12345678.95));
        System.out.println(formatDouble);

//        decimalFormatExample();
    }


    private static void decimalFormatExample(){

        // Print out a number using the localized number, integer, currency,
        // and percent format for each locale
//        Locale[] locales = NumberFormat.getAvailableLocales();
        Locale[] locales = new Locale[]{new Locale("uk", "UA")};
        double myNumber = -1234.56;
        NumberFormat form;
        for (int j=0; j<4; ++j) {
            System.out.println("FORMAT");
            for (int i = 0; i < locales.length; ++i) {
                if (locales[i].getCountry().length() == 0) {
                    continue; // Skip language-only locales
                }
                System.out.print(locales[i].getDisplayName());
                switch (j) {
                    case 0:
                        form = NumberFormat.getInstance(locales[i]); break;
                    case 1:
                        form = NumberFormat.getIntegerInstance(locales[i]); break;
                    case 2:
                        form = NumberFormat.getCurrencyInstance(locales[i]); break;
                    default:
                        form = NumberFormat.getPercentInstance(locales[i]); break;
                }
                if (form instanceof DecimalFormat) {
                    System.out.print(": " + ((DecimalFormat) form).toPattern());
                }
                System.out.print(" -> " + form.format(myNumber));
                try {
                    System.out.println(" -> " + form.parse(form.format(myNumber)));
                } catch (ParseException e) {}
            }
        }
    }
}
