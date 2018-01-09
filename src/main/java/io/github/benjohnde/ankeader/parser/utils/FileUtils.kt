package io.github.benjohnde.ankeader.parser.utils

import java.io.File
import java.io.FileWriter
import java.io.IOException

object FileUtils {
    @Throws(IOException::class)
    fun serve(dirName: String): File {
        val file = File.createTempFile(dirName, "")
        // create temporary file, delete it to retrieve tmp path
        file.delete()
        // create temporary directory
        file.mkdir()
        return file
    }

    @Throws(IOException::class)
    fun remove(tmp: File) {
        tmp.deleteRecursively()
    }

    @Throws(IOException::class)
    fun save(file: File, content: String) {
        val fooWriter = FileWriter(file)
        fooWriter.write(content)
        fooWriter.close()
    }
}
