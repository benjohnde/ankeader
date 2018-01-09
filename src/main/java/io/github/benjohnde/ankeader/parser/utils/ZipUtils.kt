package io.github.benjohnde.ankeader.parser.utils

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

object ZipUtils {
    @Throws(IOException::class)
    fun unzip(filename: String, toDirectory: File) {
        val buffer = ByteArray(1024)
        val zis = ZipInputStream(FileInputStream(filename))
        var zipEntry: ZipEntry? = zis.nextEntry
        while (zipEntry != null) {
            val fileName = zipEntry.name
            val newFile = File(toDirectory.absolutePath, fileName)
            val fos = FileOutputStream(newFile)
            var len: Int = zis.read(buffer)
            while (len > 0) {
                fos.write(buffer, 0, len)
                len = zis.read(buffer)
            }
            fos.close()
            zipEntry = zis.nextEntry
        }
        zis.closeEntry()
        zis.close()
    }
}
