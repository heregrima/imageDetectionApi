package com.heb.imageScanner.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonValueExtractor {
    public static List<Object> getAllValuesForKey(Object jsonObjectOrArray, String key) {
        List<Object> values = new ArrayList<>();
        if (jsonObjectOrArray instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) jsonObjectOrArray;
            for (String currentKey : jsonObject.keySet()) {
                Object value = jsonObject.get(currentKey);
                if (currentKey.equals(key)) {
                    values.add(value);
                }
                if (value instanceof JSONObject || value instanceof JSONArray) {
                    values.addAll(getAllValuesForKey(value, key)); // Recurse for nested objects/arrays
                }
            }
        } else if (jsonObjectOrArray instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) jsonObjectOrArray;
            for (Object item : jsonArray) {
                if (item instanceof JSONObject || item instanceof JSONArray) {
                    values.addAll(getAllValuesForKey(item, key)); // Recurse for items in array
                }
            }
        }
        return values;
    }
}
