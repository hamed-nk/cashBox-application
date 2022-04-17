package com.demisco.cashbox.util;

import org.springframework.util.StringUtils;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public final class LocaleUtils {
    public static final String RTL_CHAR = "\u200f";
    public static final Locale FARSI_IRAN_LOCALE = new Locale("fa", "IR");
    private final static char[][] ENGLISH_PERSIAN_NUMBER = {
            {'0', '\u06F0'},
            {'1', '\u06F1'},
            {'2', '\u06F2'},
            {'3', '\u06F3'},
            {'4', '\u06F4'},
            {'5', '\u06F5'},
            {'6', '\u06F6'},
            {'7', '\u06F7'},
            {'8', '\u06F8'},
            {'9', '\u06F9'},};

    /**
     * This method checks the locale and return its Orientation Type
     *
     * @param locale locale to check its orientation
     * @return orientation of the locale
     * @see java.awt.ComponentOrientation
     */
    public static OrientationType getOrientation(Locale locale) {
        String lang = locale.getLanguage();
        if ("iw".equals(lang) || "ar".equals(lang)
                || "fa".equals(lang) || "ur".equals(lang)) {
            return OrientationType.RIGHT_TO_LEFT;
        } else {
            return OrientationType.LEFT_TO_RIGHT;
        }
    }

    /**
     * This method calls getOrientation(Locale) from current locale using
     * LocaleContextHolder
     *
     * @return orientation of the locale
     */
    public static OrientationType getCurrentOrientation() {
        return getOrientation(Locale.getDefault());
    }

    public static String convertEnglishNumbersToPersian(String text) {
        if (StringUtils.isEmpty(text))
            return null;
        for (char[] chars : ENGLISH_PERSIAN_NUMBER) {
            text = text.replaceAll(String.valueOf(chars[0]), String.valueOf(chars[1]));
        }
        return text;
    }

    /**
     * Abbreviation name method for convertEnglishNumbersToPersian
     *
     * @param text
     * @return
     */
    public static String persianNum(String text) {
        return convertEnglishNumbersToPersian(text);
    }


    public static String groupNumber(Number input) {
        if (input == null)
            return "";
        Locale currentLocale = Locale.getDefault();
        NumberFormat currencyInstance;
        boolean rtl = getOrientation(currentLocale) == OrientationType.RIGHT_TO_LEFT;
        if (rtl)
            currencyInstance = NumberFormat.getNumberInstance(currentLocale);
        else
            currencyInstance = NumberFormat.getNumberInstance(currentLocale);
        currencyInstance.setGroupingUsed(true);
        currencyInstance.setMaximumFractionDigits(20);
        currencyInstance.setMinimumFractionDigits(0);
        return currencyInstance.format(input);
    }

    public static String formatCurrency(Number input) {
        if (input == null)
            return "";
        Locale currentLocale = Locale.getDefault();
        NumberFormat currencyInstance;
        boolean rtl = getOrientation(currentLocale) == OrientationType.RIGHT_TO_LEFT;
        if (rtl)
            currencyInstance = NumberFormat.getNumberInstance(currentLocale);
        else
            currencyInstance = NumberFormat.getCurrencyInstance(currentLocale);
        currencyInstance.setGroupingUsed(true);
        currencyInstance.setMaximumFractionDigits(20);
        currencyInstance.setMinimumFractionDigits(0);
        String format = currencyInstance.format(input);
        if (rtl) {
            return format + " " + Currency.getInstance(currentLocale).getSymbol(currentLocale);
        }
        return currencyInstance.format(input);
    }

    public enum OrientationType {
        LEFT_TO_RIGHT {
            @Override
            public String toString() {
                return "ltr";
            }
        }, RIGHT_TO_LEFT {
            @Override
            public String toString() {
                return "rtl";
            }
        }
    }
}
