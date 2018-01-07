package io.github.benjohnde.ankeader.anki;

import io.github.benjohnde.ankeader.parser.orm.CardEntity;
import io.github.benjohnde.ankeader.parser.utils.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ApkgCard {
    private final List<String> tags;
    private final String question;
    private final String answer;

    public ApkgCard(List<String> tags, String question, String answer) {
        this.tags = tags;
        this.question = question;
        this.answer = answer;
    }

    public ApkgCard(CardEntity cardEntity) {
        String tags = cardEntity.getTags().trim();
        this.tags = Collections.unmodifiableList(Arrays.asList(tags.split(" ")));

        List<String> slices = StringUtils.sliceBy((char) 0x1f, cardEntity.getFlds());
        this.question = slices.get(0);
        this.answer = slices.get(1);
    }

    public List<String> getTags() {
        return tags;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
