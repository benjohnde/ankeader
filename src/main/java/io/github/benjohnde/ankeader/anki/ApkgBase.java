package io.github.benjohnde.ankeader.anki;

import java.util.List;
import java.util.Map;

public class ApkgBase {
    private List<ApkgCard> cards;
    private Map<String, String> media;

    public List<ApkgCard> getCards() {
        return cards;
    }

    public void setCards(List<ApkgCard> cards) {
        this.cards = cards;
    }

    public Map<String, String> getMedia() {
        return media;
    }

    public void setMedia(Map<String, String> media) {
        this.media = media;
    }
}
