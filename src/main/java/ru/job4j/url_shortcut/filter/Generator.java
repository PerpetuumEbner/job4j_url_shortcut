package ru.job4j.url_shortcut.filter;

import liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils;

public class Generator {
    public static String valueGenerator(int size) {
        return RandomStringUtils.randomAlphabetic(size);
    }
}