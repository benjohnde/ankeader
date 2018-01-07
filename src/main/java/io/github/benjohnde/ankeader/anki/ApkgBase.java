package io.github.benjohnde.ankeader.anki;

import java.util.List;

public class ApkgBase {
    private List<ApkgCard> cards;
    private ApkgMedia media;

    public ApkgBase(List<ApkgCard> cards, ApkgMedia media) {
        this.cards = cards;
        this.media = media;
    }
}
