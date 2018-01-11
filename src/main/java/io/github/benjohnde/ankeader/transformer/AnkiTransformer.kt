package io.github.benjohnde.ankeader.transformer

import io.github.benjohnde.ankeader.anki.AnkiCard
import io.github.benjohnde.ankeader.parser.orm.CardEntity
import io.github.benjohnde.ankeader.parser.utils.StringUtils
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

class AnkiTransformer {
    fun transformCards(cards: List<CardEntity>): List<AnkiCard> {
        return cards.map {
            var tags = it.tags!!.trim().split(" ")

            if (!tags.toString().contains("Block")) {
                tags = tags.plus("Block00")
            }

            val id = it.id
            val slices = StringUtils.sliceBy(0x1f.toChar(), it.flds!!)
            val question = slices[0].trim()
            val answer = slices[1].trim()

            AnkiCard(id, tags, question, answer)
        }.filterNot {
            it.id == 1359886655736
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
