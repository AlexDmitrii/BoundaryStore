package com.MongoBoundary.util;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

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

    public static Query getQueryById(String orderId){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(orderId));

        return query;
    }

    public static Update fillDataForUpdate (Map<String, Object> data){
        Update update = new Update();
        for (String key : data.keySet()){
            update.set(key, data.get(key));
        }

        return update;
    }

    public static String getBase64FromArrayBytes(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }
}
