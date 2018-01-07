package io.github.benjohnde.ankeader.parser.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class JsonUtils {
    public static Map<String, String> getMapFromJson(String json) {
        Type type = new TypeToken<Map<String, String>>() {}.getType();
        return new Gson().fromJson(json, type);
    }
}
