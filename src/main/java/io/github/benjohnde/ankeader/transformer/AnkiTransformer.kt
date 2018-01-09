package io.github.benjohnde.ankeader.transformer

import io.github.benjohnde.ankeader.anki.AnkiCard
import io.github.benjohnde.ankeader.parser.orm.CardEntity
import io.github.benjohnde.ankeader.parser.utils.StringUtils

import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.Arrays
import java.util.Collections
import java.util.stream.Collectors

class AnkiTransformer {
    fun transformCards(cards: List<CardEntity>): List<AnkiCard> {
        return cards.map {
            val tags = it.tags!!.trim().split(" ")
            val slices = StringUtils.sliceBy(0x1f.toChar(), it.flds!!)
            val question = slices[0]
            val answer = slices[1]

            AnkiCard(tags, answer, question)
        }
    }

    fun transformMedia(media: Map<String, String>, sourcePath: String, destPath: String) {
        media.forEach { id, filename ->
            val outputDirectory = File(destPath, "media")
            outputDirectory.mkdirs()

            val src = Paths.get(sourcePath, id)
            val dest = Paths.get(outputDirectory.absolutePath, filename)
            try {
                Files.move(src, dest)
            } catch (e: IOException) {
                // file may be not present
            }
        }
    }
}
