package io.github.benjohnde.ankeader.parser.orm

import org.sormula.Database
import org.sqlite.SQLiteConfig
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.util.*

class CollectionReader @Throws(Exception::class)
constructor(tmp: String) {
    private var connection: Connection? = null
    private var database: Database? = null
    val cards: List<CardEntity>

    init {
        val databaseFile = File(tmp, "collection.anki2")
        initSqliteConnection(databaseFile)
        this.cards = readCards()

        // finally
        connection!!.close()
    }

    @Throws(Exception::class)
    private fun initSqliteConnection(databaseFile: File) {
        Class.forName("org.sqlite.JDBC")

        val config = SQLiteConfig()
        config.setReadOnly(true)

        val url = "jdbc:sqlite:" + databaseFile.absolutePath

        connection = DriverManager.getConnection(url, config.toProperties())
        database = Database(connection)
    }

    @Throws(Exception::class)
    private fun readCards(): List<CardEntity> {
        val tableCards = database!!.getTable(CardEntity::class.java)
        return Collections.unmodifiableList(tableCards.selectAll())
    }
}
