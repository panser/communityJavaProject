package ua.org.gostroy.javacore.numbertostring;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by s.panov on 12/3/2015.
 *
 * this code from https://github.com/javadev/moneytostr-russian
 */

public class MoneyToStr {
    private static final int INDEX_3 = 3;
    private static final int INDEX_2 = 2;
    private static final int INDEX_1 = 1;
    private static final int INDEX_0 = 0;
    private static final int NUM0 = 0;
    private static final int NUM1 = 1;
    private static final int NUM2 = 2;
    private static final int NUM3 = 3;
    private static final int NUM4 = 4;
    private static final int NUM5 = 5;
    private static final int NUM6 = 6;
    private static final int NUM7 = 7;
    private static final int NUM8 = 8;
    private static final int NUM9 = 9;
    private static final int NUM10 = 10;
    private static final int NUM11 = 11;
    private static final int NUM14 = 14;
    private static final int NUM100 = 100;
    private static final int NUM1000 = 1000;
    private static final int NUM10000 = 10000;

    private static Document xmlDoc;
    private static String xmlFileName = "currencyList.xml";
    private final Map<String, String[]> messages = new LinkedHashMap<String, String[]>();
    private final String rubOneUnit;
    private final String rubTwoUnit;
    private final String rubFiveUnit;
    private final String rubSex;
    private final String kopOneUnit;
    private final String kopTwoUnit;
    private final String kopFiveUnit;
    private final String kopSex;
    private final String rubShortUnit;
    private final Currency currency;
    private final Language language;
    private final Pennies pennies;

    static {
        initXmlDoc();
    }

    public static void initXmlDoc() {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
//            InputStream is = ClassLoader.getSystemResourceAsStream(xmlFileName);
//            byte[] bytes = IOUtils.toByteArray(is);
            URL url = ClassLoader.getSystemResource(xmlFileName);
            byte[] bytesFromFile = Files.readAllBytes(Paths.get(url.toURI()));

            DocumentBuilder xmlDocBuilder = docFactory.newDocumentBuilder();
            xmlDoc = xmlDocBuilder.parse(new java.io.ByteArrayInputStream(bytesFromFile));
        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex);
        }
    }

    /**
     * Currency.
     */
    public enum Currency {
        /**
         * .
         */
        RUR,
        /**
         * .
         */
        UAH,
        /**
         * .
         */
        USD,
        /**
         * .
         */
        PER10,
        /**
         * .
         */
        PER100,
        /**
         * .
         */
        PER1000,
        /**
         * .
         */
        PER10000,
        /**
         * .
         */
        Custom
    }

    /**
     * Language.
     */
    public enum Language {
        /**
         * .
         */
        RUS,
        /**
         * .
         */
        UKR,
        /**
         * .
         */
        ENG
    }

    /**
     * Pennies.
     */
    public enum Pennies {
        /**
         * .
         */
        NUMBER,
        /**
         * .
         */
        TEXT
    }

    /**
     * Inits class with currency. Usage: MoneyToStr moneyToStr = new MoneyToStr(
     * MoneyToStr.Currency.UAH, MoneyToStr.Language.UKR, MoneyToStr.Pennies.NUMBER);
     * Definition for currency is placed into currlist.xml
     *
     * @param currency the currency (UAH, RUR, USD)
     * @param language the language (UKR, RUS, ENG)
     * @param pennies  the pennies (NUMBER, TEXT)
     */
    public MoneyToStr(Currency currency, Language language, Pennies pennies) {
        if (currency == null) {
            throw new IllegalArgumentException("currency is null");
        }
        if (language == null) {
            throw new IllegalArgumentException("language is null");
        }
        if (pennies == null) {
            throw new IllegalArgumentException("pennies is null");
        }
        this.currency = currency;
        this.language = language;
        this.pennies = pennies;
        String theISOstr = currency.name();
        Element languageElement = (Element)
                (xmlDoc.getElementsByTagName(language.name())).item(0);
        NodeList items = languageElement.getElementsByTagName("item");
        for (int index = 0; index < items.getLength(); index += 1) {
            Element languageItem = (Element) items.item(index);
            messages.put(languageItem.getAttribute("value"), languageItem.getAttribute("text").split(","));
        }
        NodeList theISOElements = (NodeList) (xmlDoc.getElementsByTagName(theISOstr));
        Element theISOElement = null;
        for (int index = 0; index < theISOElements.getLength(); index += 1) {
            if (((Element) theISOElements.item(index)).getAttribute("language").equals(language.name())) {
                theISOElement = (Element) theISOElements.item(index);
                break;
            }
        }
        rubOneUnit = theISOElement.getAttribute("RubOneUnit");
        rubTwoUnit = theISOElement.getAttribute("RubTwoUnit");
        rubFiveUnit = theISOElement.getAttribute("RubFiveUnit");
        kopOneUnit = theISOElement.getAttribute("KopOneUnit");
        kopTwoUnit = theISOElement.getAttribute("KopTwoUnit");
        kopFiveUnit = theISOElement.getAttribute("KopFiveUnit");
        rubSex = theISOElement.getAttribute("RubSex");
        kopSex = theISOElement.getAttribute("KopSex");
        rubShortUnit = theISOElement.hasAttribute("RubShortUnit") ? theISOElement.getAttribute("RubShortUnit") : "";
    }

    /**
     * Inits class with currency. Usage: MoneyToStr moneyToStr = new MoneyToStr(
     * MoneyToStr.Currency.UAH, MoneyToStr.Language.UKR, MoneyToStr.Pennies.NUMBER);
     *
     * @param currency the currency (UAH, RUR, USD)
     * @param language the language (UKR, RUS, ENG)
     * @param pennies  the pennies (NUMBER, TEXT)
     * @param names    the custom names
     */
    public MoneyToStr(Currency currency, Language language, Pennies pennies, String[] names) {
        if (currency == null) {
            throw new IllegalArgumentException("currency is null");
        }
        if (language == null) {
            throw new IllegalArgumentException("language is null");
        }
        if (pennies == null) {
            throw new IllegalArgumentException("pennies is null");
        }
        if (names == null || names.length != 8) {
            throw new IllegalArgumentException("names is null");
        }
        this.currency = currency;
        this.language = language;
        this.pennies = pennies;
        Element languageElement = (Element)
                (xmlDoc.getElementsByTagName(language.name())).item(0);
        NodeList items = languageElement.getElementsByTagName("item");
        for (int index = 0; index < items.getLength(); index += 1) {
            Element languageItem = (Element) items.item(index);
            messages.put(languageItem.getAttribute("value"), languageItem.getAttribute("text").split(","));
        }
        rubOneUnit = names[0];
        rubTwoUnit = names[1];
        rubFiveUnit = names[2];
        rubSex = names[3];
        kopOneUnit = names[4];
        kopTwoUnit = names[5];
        kopFiveUnit = names[6];
        kopSex = names[7];
        rubShortUnit = names[0];
    }

    /**
     * Converts percent to string.
     *
     * @param amount the amount of percent
     * @param lang   the language (RUS, UKR)
     * @return the string of percent
     */
    public static String percentToStr(Double amount, Language lang) {
        return percentToStr(amount, lang, Pennies.TEXT);
    }

    /**
     * Converts percent to string.
     *
     * @param amount  the amount of percent
     * @param lang    the language (RUS, UKR, ENG)
     * @param pennies the pennies (NUMBER, TEXT)
     * @return the string of percent
     */
    public static String percentToStr(Double amount, Language lang, Pennies pennies) {
        if (amount == null) {
            throw new IllegalArgumentException("amount is null");
        }
        if (lang == null) {
            throw new IllegalArgumentException("language is null");
        }
        if (pennies == null) {
            throw new IllegalArgumentException("pennies is null");
        }
        Long intPart = amount.longValue();
        Long fractPart = 0L;
        String result;
        if (amount.floatValue() == amount.intValue()) {
            result = new MoneyToStr(Currency.PER10, lang, pennies).convert(amount.longValue(), 0L);
        } else if (Double.valueOf(amount * NUM10).floatValue() == Double.valueOf(amount * NUM10).intValue()) {
            fractPart = Math.round((amount - intPart) * NUM10);
            result = new MoneyToStr(Currency.PER10, lang, pennies).convert(intPart, fractPart);
        } else if (Double.valueOf(amount * NUM100).floatValue() == Double.valueOf(amount * NUM100).intValue()) {
            fractPart = Math.round((amount - intPart) * NUM100);
            result = new MoneyToStr(Currency.PER100, lang, pennies).convert(intPart, fractPart);
        } else if (Double.valueOf(amount * NUM1000).floatValue() == Double.valueOf(amount * NUM1000).intValue()) {
            fractPart = Math.round((amount - intPart) * NUM1000);
            result = new MoneyToStr(Currency.PER1000, lang, pennies).convert(intPart, fractPart);
        } else {
            fractPart = Math.round((amount - intPart) * NUM10000);
            result = new MoneyToStr(Currency.PER10000, lang, pennies).convert(intPart, fractPart);
        }
        return result;
    }

    /**
     * Converts double value to the text description.
     *
     * @param theMoney the amount of money in format major.minor
     * @return the string description of money value
     */
    public String convert(Double theMoney) {
        if (theMoney == null) {
            throw new IllegalArgumentException("theMoney is null");
        }
        Long intPart = theMoney.longValue();
        Long fractPart = Math.round((theMoney - intPart) * NUM100);
        if (currency == Currency.PER1000) {
            fractPart = Math.round((theMoney - intPart) * NUM1000);
        }
        return convert(intPart, fractPart);
    }

    /**
     * Converts amount to words. Usage: MoneyToStr moneyToStr =
     * new MoneyToStr(MoneyToStr.Currency.UAH, MoneyToStr.Language.UKR, MoneyToStr.Pennies.NUMBER);
     * String result = moneyToStr.convert(123D); Expected: result = сто двадцять три гривні 00 копійок
     *
     * @param theMoney   the amount of money major currency
     * @param theKopeiki the amount of money minor currency
     * @return the string description of money value
     */
    public String convert(Long theMoney, Long theKopeiki) {
        if (theMoney == null) {
            throw new IllegalArgumentException("theMoney is null");
        }
        if (theKopeiki == null) {
            throw new IllegalArgumentException("theKopeiki is null");
        }
        StringBuilder money2str = new StringBuilder();
        Long triadNum = 0L;
        Long theTriad;

        Long intPart = theMoney;
        if (intPart == 0) {
            money2str.append(messages.get("0")[0] + " ");
        }
        do {
            theTriad = intPart % NUM1000;
            money2str.insert(0, triad2Word(theTriad, triadNum, rubSex));
            if (triadNum == 0) {
                if ((theTriad % NUM100) / NUM10 == NUM1) {
                    money2str.append(rubFiveUnit);
                } else {
                    switch (Long.valueOf(theTriad % NUM10).byteValue()) {
                        case NUM1:
                            money2str.append(rubOneUnit);
                            break;
                        case NUM2:
                        case NUM3:
                        case NUM4:
                            money2str.append(rubTwoUnit);
                            break;
                        default:
                            money2str.append(rubFiveUnit);
                            break;
                    }
                }
            }
            intPart /= NUM1000;
            triadNum++;
        } while (intPart > 0);

        if (pennies == Pennies.TEXT) {
            money2str.append(language == Language.ENG ? " and " : " ").append(
                    theKopeiki == 0 ? messages.get("0")[0] + " " : triad2Word(theKopeiki, 0L, kopSex));
        } else {
            money2str.append(" " + (theKopeiki < 10 ? "0" + theKopeiki : theKopeiki) + " ");
        }
        if (theKopeiki >= NUM11 && theKopeiki <= NUM14) {
            money2str.append(kopFiveUnit);
        } else {
            switch ((byte) (theKopeiki % NUM10)) {
                case NUM1:
                    money2str.append(kopOneUnit);
                    break;
                case NUM2:
                case NUM3:
                case NUM4:
                    money2str.append(kopTwoUnit);
                    break;
                default:
                    money2str.append(kopFiveUnit);
                    break;
            }
        }
        return money2str.toString().trim();
    }

    private String triad2Word(Long triad, Long triadNum, String sex) {
        final StringBuilder triadWord = new StringBuilder(NUM100);

        if (triad == 0) {
            return "";
        }

        triadWord.append(concat(new String[]{""}, messages.get("100_900"))[Long.valueOf(triad / NUM100).byteValue()]);
        final Long range10 = (triad % NUM100) / NUM10;
        triadWord.append(concat(new String[]{"", ""}, messages.get("20_90"))[range10.byteValue()]);
        if (language == Language.ENG && triadWord.length() > 0 && triad % NUM10 == 0) {
            triadWord.deleteCharAt(triadWord.length() - 1);
            triadWord.append(" ");
        }

        check2(triadNum, sex, triadWord, triad, range10);
        switch (triadNum.byteValue()) {
            case NUM0:
                break;
            case NUM1:
            case NUM2:
            case NUM3:
            case NUM4:
                if (range10 == NUM1) {
                    triadWord.append(messages.get("1000_10")[triadNum.byteValue() - 1] + " ");
                } else {
                    final Long range = triad % NUM10;
                    switch (range.byteValue()) {
                        case NUM1:
                            triadWord.append(messages.get("1000_1")[triadNum.byteValue() - 1] + " ");
                            break;
                        case NUM2:
                        case NUM3:
                        case NUM4:
                            triadWord.append(messages.get("1000_234")[triadNum.byteValue() - 1] + " ");
                            break;
                        default:
                            triadWord.append(messages.get("1000_5")[triadNum.byteValue() - 1] + " ");
                            break;
                    }
                }
                break;
            default:
                triadWord.append("??? ");
                break;
        }
        return triadWord.toString();
    }

    /**
     * @param triadNum  the triad num
     * @param sex       the sex
     * @param triadWord the triad word
     * @param triad     the triad
     * @param range10   the range 10
     */
    private void check2(Long triadNum, String sex, StringBuilder triadWord, Long triad, Long range10) {
        final Long range = triad % NUM10;
        if (range10 == 1) {
            triadWord.append(messages.get("10_19")[range.byteValue()] + " ");
        } else {
            switch (range.byteValue()) {
                case NUM1:
                    if (triadNum == NUM1) {
                        triadWord.append(messages.get("1")[INDEX_0] + " ");
                    } else if (triadNum == NUM2 || triadNum == NUM3 || triadNum == NUM4) {
                        triadWord.append(messages.get("1")[INDEX_1] + " ");
                    } else if ("M".equals(sex)) {
                        triadWord.append(messages.get("1")[INDEX_2] + " ");
                    } else if ("F".equals(sex)) {
                        triadWord.append(messages.get("1")[INDEX_3] + " ");
                    }
                    break;
                case NUM2:
                    if (triadNum == NUM1) {
                        triadWord.append(messages.get("2")[INDEX_0] + " ");
                    } else if (triadNum == NUM2 || triadNum == NUM3 || triadNum == NUM4) {
                        triadWord.append(messages.get("2")[INDEX_1] + " ");
                    } else if ("M".equals(sex)) {
                        triadWord.append(messages.get("2")[INDEX_2] + " ");
                    } else if ("F".equals(sex)) {
                        triadWord.append(messages.get("2")[INDEX_3] + " ");
                    }
                    break;
                case NUM3:
                case NUM4:
                case NUM5:
                case NUM6:
                case NUM7:
                case NUM8:
                case NUM9:
                    triadWord.append(concat(new String[]{"", "", ""}, messages.get("3_9"))[range.byteValue()] + " ");
                    break;
                default:
                    break;
            }
        }
    }

    private <T> T[] concat(T[] first, T[] second) {
        final T[] result = java.util.Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public static void main(String[] args) {
        String amount = "123.25";
        String language = "ENG";
        String currency = "USD";
        String pennies = "TEXT";
        if (args.length == 0) {
            System.out.println("Usage: java -jar moneytostr.jar --amount=123.25 --language=rus|ukr|eng --currency=rur|uah|usd --pennies=text|number");
        } else {
            for (String arg : args) {
                if (arg.startsWith("--amount=")) {
                    amount = arg.substring("--amount=".length()).trim().replace(",", ".");
                } else if (arg.startsWith("--language=")) {
                    language = arg.substring("--language=".length()).trim().toUpperCase();
                } else if (arg.startsWith("--currency=")) {
                    currency = arg.substring("--currency=".length()).trim().toUpperCase();
                } else if (arg.startsWith("--pennies=")) {
                    pennies = arg.substring("--pennies=".length()).trim().toUpperCase();
                }
            }
            String result = new MoneyToStr(Currency.valueOf(currency), Language.valueOf(language), Pennies.valueOf(pennies)).convert(Double.valueOf(amount));
            System.out.println(result);
        }
    }

    public Map<String, String[]> getMessages() {
        return messages;
    }

    public String getRubShortUnit() {
        return rubShortUnit;
    }

    public Language getLanguage() {
        return language;
    }
}
