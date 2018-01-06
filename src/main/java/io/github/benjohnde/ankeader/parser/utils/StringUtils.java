package io.github.benjohnde.ankeader.parser.utils;

import java.util.LinkedList;
import java.util.List;

public class StringUtils {
    public static List<String> sliceBy(char separator, String sequence) {
        List<String> slices = new LinkedList<>();

        int start = 0;
        int length = sequence.length();
        for (int pos = 0; pos < sequence.length(); pos++) {
            if (sequence.charAt(pos) == separator) {
                slices.add(sequence.substring(start, pos));
                start = pos + 1;
            }
        }

        if (start != length) {
            slices.add(sequence.substring(start, length));
        }

        return slices;
    }
}
