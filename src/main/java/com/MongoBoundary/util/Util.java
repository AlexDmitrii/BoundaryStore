package com.MongoBoundary.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
        return capitalizeWords(formatter.format(new Date()));
    }

    public static String capitalizeWords(String input) {
        if (input == null || input.isEmpty())
            return input;

        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : input.trim().toCharArray()) {
            c = capitalizeNext ? Character.toUpperCase(c) : c;
            capitalizeNext = c == ' ';
            result.append(c);
        }

        return result.toString();
    }
}
