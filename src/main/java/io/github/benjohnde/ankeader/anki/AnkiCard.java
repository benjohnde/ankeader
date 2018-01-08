package io.github.benjohnde.ankeader.anki;

import java.util.List;

public class AnkiCard {
    private final List<String> tags;
    private final String question;
    private final String answer;

    public AnkiCard(List<String> tags, String question, String answer) {
        this.tags = tags;
        this.question = question;
        this.answer = answer;
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
