package io.github.benjohnde.ankeader.parser.orm;

import org.sormula.annotation.Column;
import org.sormula.annotation.Row;

@Row(tableName = "notes")
public class CardEntity {
    @Column(primaryKey = true)
    private long id;

    @Column(name = "tags")
    private String tags;

    @Column(name = "flds")
    private String flds;

    // Getter / setter for sormula ORM

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlds() {
        return flds;
    }

    public void setFlds(String flds) {
        this.flds = flds;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
