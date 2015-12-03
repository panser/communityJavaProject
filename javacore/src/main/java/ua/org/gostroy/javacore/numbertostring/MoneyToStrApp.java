package ua.org.gostroy.javacore.numbertostring;

/**
 * Created by s.panov on 12/3/2015.
 */
public class MoneyToStrApp {


    public static void main(String[] args) {

        String result;
//        String value = "23";
//        Double summ = Double.valueOf(value);
        Double summ = 123d;

//        String result = MoneyToStr.percentToStr(summ, moneyToStrTxt.getLanguage());
//        System.out.println(result);
//
//        result = MoneyToStr.percentToStr(summ, moneyToStrTxt.getLanguage(), MoneyToStr.Pennies.NUMBER);
//        System.out.println(result);

        MoneyToStr moneyToStrTxt = new MoneyToStr(MoneyToStr.Currency.UAH, MoneyToStr.Language.UKR, MoneyToStr.Pennies.TEXT);
        result = moneyToStrTxt.convert(summ);
        System.out.println(result);
    }
}
