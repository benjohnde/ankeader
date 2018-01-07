package io.github.benjohnde.ankeader.anki;

import java.util.List;

public class ApkgBase {
    private final List<ApkgCard> cards;
    private final ApkgMedia media;

    public ApkgBase(List<ApkgCard> cards, ApkgMedia media) {
        this.cards = cards;
        this.media = media;
    }
}
