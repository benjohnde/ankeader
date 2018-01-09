package io.github.benjohnde.ankeader.parser.utils

import java.util.Collections
import java.util.LinkedList

object StringUtils {
    fun sliceBy(separator: Char, sequence: String): List<String> {
        val slices = LinkedList<String>()

        var start = 0
        val length = sequence.length
        for (pos in 0 until sequence.length) {
            if (sequence[pos] == separator) {
                slices.add(sequence.substring(start, pos))
                start = pos + 1
            }
        }

        if (start != length) {
            slices.add(sequence.substring(start, length))
        }

        return Collections.unmodifiableList(slices)
    }
}
