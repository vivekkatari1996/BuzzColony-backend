package com.idea.buzzcolony.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class AppMessage {

    @Autowired
    MessageSource messageSource;

    public String getMessage(String code, Object[] args) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, currentLocale);
    }

    public String getMessage(String code) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, currentLocale);
    }
}