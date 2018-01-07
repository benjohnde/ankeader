package io.github.benjohnde.ankeader.parser.collection;

import io.github.benjohnde.ankeader.parser.utils.StringUtils;
import org.sormula.annotation.Column;
import org.sormula.annotation.Row;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Row(tableName = "notes")
public class Card {
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

    // Custom methods

    private List<String> getTagsTokenized() {
        if (tags == null) {
            return new ArrayList<>();
        }
        tags = tags.trim();
        return Collections.unmodifiableList(Arrays.asList(tags.split(" ")));
    }

    public String getQuestion() {
        if (flds == null) {
            return "";
        }
        return StringUtils.sliceBy((char) 0x1f, flds).get(0);
    }

    public String getAnswer() {
        if (flds == null) {
            return "";
        }
        return StringUtils.sliceBy((char) 0x1f, flds).get(1);
    }

    @Override
    public String toString() {
        return "Card {\n" +
                // "id=" + id + ",\n" +
                "tags='" + getTagsTokenized() + '\'' + ",\n" +
                "question='" + StringUtils.html2text(getQuestion()) + '\'' + ",\n" +
                "answer='" + StringUtils.html2text(getAnswer()) + '\'' + ",\n" +
                '}';
    }
}
