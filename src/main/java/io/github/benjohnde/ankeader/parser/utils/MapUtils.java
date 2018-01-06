package io.github.benjohnde.ankeader.parser.utils;

import java.util.Map;
import java.util.Objects;

public class MapUtils {
    // @see https://stackoverflow.com/a/2904266
    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
