package io.github.benjohnde.ankeader

import io.github.benjohnde.ankeader.generator.DocGenerator
import io.github.benjohnde.ankeader.parser.ApkgReader
import io.github.benjohnde.ankeader.parser.utils.FileUtils
import io.github.benjohnde.ankeader.transformer.AnkiTransformer
import javafx.application.Application
import java.io.File
import java.nio.file.Paths

object Ankeader {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        println("Starting Ankeader...")
        println("")

        val home = System.getProperty("user.home")

        val input = Paths.get(home, "Desktop/ankeader/SIP1_bis_2017-09b.apkg").toString()
        val output = Paths.get(home, "Desktop/ankeader/document/").toString()

        val reader = ApkgReader(input)
        reader.run()

        val transformer = AnkiTransformer()
        val cards = transformer.transformCards(reader.cards!!)
        transformer.transformMedia(reader.media!!, input, output)

        // cluster in blocks (BlockXX)
        cards.forEach {
            val tag = it.tags.filter { it.contains("Block") }
            println(tag)
        }

        val docGen = DocGenerator(cards)
        val doc = docGen.gen()

        val docFile = File(output, "index.html")
        FileUtils.save(docFile, doc)

        reader.cleanup()

        Application.launch(MinimalisticUI::class.java)
    }
}
