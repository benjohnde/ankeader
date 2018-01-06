package io.github.benjohnde.ankeader.parser.collection;

import org.sormula.annotation.Column;
import org.sormula.annotation.Row;

@Row(tableName = "notes")
public class Note {
    @Column(primaryKey = true)
    private long id;

    @Column(name = "tags")
    private String tags;

    @Column(name = "flds")
    private String question;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
