package io.github.benjohnde.ankeader.parser

import io.github.benjohnde.ankeader.parser.orm.CardEntity
import io.github.benjohnde.ankeader.parser.orm.CollectionReader
import io.github.benjohnde.ankeader.parser.utils.FileUtils
import io.github.benjohnde.ankeader.parser.utils.JsonUtils
import io.github.benjohnde.ankeader.parser.utils.ZipUtils

import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

class ApkgReader(private val input: String) {
    var tmp: File? = null
        private set

    var media: Map<String, String>? = null
        private set
    var cards: List<CardEntity>? = null
        private set

    @Throws(Exception::class)
    fun run() {
        unleashApkgContents()
        readMedia()
        readSqlite()
    }

    @Throws(IOException::class)
    fun cleanup() {
        FileUtils.remove(tmp!!)
    }

    @Throws(IOException::class)
    private fun unleashApkgContents() {
        tmp = FileUtils.serve(this.input)
        println("Operating on: " + tmp!!)
        ZipUtils.unzip(this.input, tmp!!)
    }

    @Throws(IOException::class)
    private fun readMedia() {
        val jsonFile = String(Files.readAllBytes(Paths.get(tmp!!.absolutePath, "media")))
        this.media = JsonUtils.getMapFromJson(jsonFile)
    }

    @Throws(Exception::class)
    private fun readSqlite() {
        val collectionReader = CollectionReader(tmp!!.absolutePath)
        this.cards = collectionReader.cards
    }
}
