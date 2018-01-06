package io.github.benjohnde.ankeader.parser.collection;

import io.github.benjohnde.ankeader.parser.utils.StringUtils;
import org.sormula.annotation.Column;
import org.sormula.annotation.Row;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Row(tableName = "notes")
public class Note {
    @Column(primaryKey = true)
    private long id;

    @Column(name = "tags")
    private String tags;

    @Column(name = "flds")
    private String flds;

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

    public List<String> getAnswers() {
        if (flds == null) {
            return new ArrayList<>();
        }
        List<String> slices = StringUtils.sliceBy((char) 0x1f, flds);
        slices.remove(0);
        return slices;
    }

    @Override
    public String toString() {
        return "Note {\n" +
                "id=" + id + ",\n" +
                "tags='" + getTagsTokenized() + '\'' + ",\n" +
                "question='" + getQuestion() + '\'' + ",\n" +
                "answers='" + getAnswers() + '\'' + ",\n" +
                '}';
    }
}
