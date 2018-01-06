package io.github.benjohnde.ankeader.parser.collection;

import org.sormula.annotation.Column;
import org.sormula.annotation.Row;


@Row(tableName = "notes")
public class Card {
    @Column(primaryKey = true)
    private long id;

    @Column(name = "nid")
    private long noteId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }
}
