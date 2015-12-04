package ua.org.gostroy.javacore.numbertostring;

/**
 * Created by s.panov on 12/3/2015.
 */
public class MoneyToStrApp {


    public static void main(String[] args) {

        MoneyToStr moneyToStrTxt = new MoneyToStr(MoneyToStr.Currency.UAH, MoneyToStr.Language.UKR, MoneyToStr.Pennies.TEXT);

        Double number = 123.10d;
        String result = moneyToStrTxt.convert(number);
        System.out.println(result);
    }
}
