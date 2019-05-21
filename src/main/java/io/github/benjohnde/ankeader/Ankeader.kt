package io.github.benjohnde.ankeader

import io.github.benjohnde.ankeader.generator.DocGenerator
import io.github.benjohnde.ankeader.parser.ApkgReader
import io.github.benjohnde.ankeader.parser.utils.FileUtils
import io.github.benjohnde.ankeader.transformer.AnkiTransformer
import java.io.File
import java.nio.file.Paths

object Ankeader {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        println("Starting Ankeader...")
        println("")

        val home = System.getProperty("user.home")

        val input = Paths.get(home, "Desktop/SIP2_bis_inkl_2018-11.apkg").toString()
        val output = Paths.get(home, "Desktop/ankeader/document/").toString()

        val reader = ApkgReader(input)
        reader.run()

        val transformer = AnkiTransformer()
        val cards = transformer.transformCards(reader.cards!!)
        transformer.transformMedia(reader.media!!, reader.tmp!!.absolutePath, output)

        // cluster in blocks (BlockXX)
        val uniqueBlocks = HashSet<String>()
        cards.forEach {
            val tags = it.tags.filter { it.contains("Block") }
            uniqueBlocks.addAll(tags)
        }

        uniqueBlocks.forEach { block ->
            // generate html
            val validCards = cards.filter { it.tags.contains(block) }
            val docGen = DocGenerator(validCards)
            val doc = docGen.gen()

            val docFile = File(output, "$block.html")
            FileUtils.save(docFile, doc)
        }

        // TODO generate index file


        reader.cleanup()
    }
}
