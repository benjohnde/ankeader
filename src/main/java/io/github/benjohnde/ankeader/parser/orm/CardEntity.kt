package io.github.benjohnde.ankeader.parser.orm

import org.sormula.annotation.Column
import org.sormula.annotation.Row

@Row(tableName = "notes")
class CardEntity {
    @Column(primaryKey = true)
    var id: Long = 0

    @Column(name = "tags")
    var tags: String? = null

    @Column(name = "flds")
    var flds: String? = null
}
