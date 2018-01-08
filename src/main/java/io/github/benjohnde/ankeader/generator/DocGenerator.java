package io.github.benjohnde.ankeader.generator;

import io.github.benjohnde.ankeader.anki.ApkgBase;
import io.github.benjohnde.ankeader.anki.ApkgCard;

import java.io.IOException;

public class DocGenerator {
    private final ApkgBase base;

    public DocGenerator(ApkgBase base) {
        this.base = base;
    }

    public String gen() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (ApkgCard card : this.base.getCards()) {
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