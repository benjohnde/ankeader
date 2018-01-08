package io.github.benjohnde.ankeader.generator;

import io.github.benjohnde.ankeader.anki.AnkiCard;

import java.io.IOException;
import java.util.List;

public class DocGenerator {
    private final List<AnkiCard> cards;

    public DocGenerator(List<AnkiCard> cards) {
        this.cards = cards;
    }

    public String gen() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (AnkiCard card : this.cards) {
            String qst = card.getQuestion();
            qst = qst.replaceAll("<img src=\"", "<img src=\"media/");

            String asw = card.getAnswer();
            asw = asw.replaceAll("<img src=\"", "<img src=\"media/");
            sb.append("<h4>#" + card.getTags() + "</h4>");
            sb.append("<h3>Frage</h3>");
            sb.append(qst);
            sb.append("<h3>Antwort</h3>");
            sb.append(asw);
            sb.append("<hr>");
        }

        return sb.toString();
    }
}
