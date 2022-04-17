package com.demisco.cashbox.serviceimpl;

import com.demisco.cashbox.service.MessageBundle;
import com.demisco.cashbox.util.LocaleUtils;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class MessageBundleImpl implements MessageBundle {

    private static final Locale DEFAULT_LOCALE;
    private static MessageSource messageSource;

    public MessageBundleImpl(MessageSource messageSource) {
        MessageBundleImpl.messageSource = messageSource;
    }

    public static String message(String key, Object... args) {
        return message(key, DEFAULT_LOCALE, args);
    }

    public static String message(String key, Locale locale, Object... args) {
        try {
            return messageSource.getMessage(key, args, locale);
        } catch (Exception var4) {
            return key;
        }
    }

    public String getMessage(String key, Object... args) {
        return this.getMessage(key, DEFAULT_LOCALE, args);
    }

    public String getMessage(String key, Locale locale, Object... args) {
        try {
            return messageSource.getMessage(key, args, locale);
        } catch (Exception var5) {
            return key;
        }
    }

    static {
        DEFAULT_LOCALE = LocaleUtils.FARSI_IRAN_LOCALE;
    }
}
