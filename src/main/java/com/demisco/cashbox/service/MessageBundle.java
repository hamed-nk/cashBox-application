package com.demisco.cashbox.service;

import java.util.Locale;

public interface MessageBundle {
    String getMessage(String var1, Object... var2);

    String getMessage(String var1, Locale var2, Object... var3);
}
