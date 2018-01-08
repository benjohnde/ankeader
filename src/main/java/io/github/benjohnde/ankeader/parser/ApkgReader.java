package io.github.benjohnde.ankeader.parser;

import io.github.benjohnde.ankeader.parser.orm.CardEntity;
import io.github.benjohnde.ankeader.parser.orm.CollectionReader;
import io.github.benjohnde.ankeader.parser.utils.FileUtils;
import io.github.benjohnde.ankeader.parser.utils.JsonUtils;
import io.github.benjohnde.ankeader.parser.utils.ZipUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ApkgReader {
    private final String input;
    private File tmp;

    private Map<String, String> media;
    private List<CardEntity> cards;

    public ApkgReader(final String input) {
        this.input = input;
    }

    public void run() throws Exception {
        unleashApkgContents();
        readMedia();
        readSqlite();
    }

    public void cleanup() throws IOException {
        FileUtils.remove(tmp);
    }

    public Map<String, String> getMedia() {
        return media;
    }

    public List<CardEntity> getCards() {
        return cards;
    }

    private void unleashApkgContents() throws IOException {
        tmp = FileUtils.serve(this.input);
        System.out.println("Operating on: " + tmp);
        ZipUtils.unzip(this.input, tmp);
    }

    private void readMedia() throws IOException {
        String jsonFile = new String(Files.readAllBytes(Paths.get(tmp.getAbsolutePath(), "media")));
        this.media = JsonUtils.getMapFromJson(jsonFile);
    }

    private void readSqlite() throws Exception {
        CollectionReader collectionReader = new CollectionReader(tmp.getAbsolutePath());
        this.cards = collectionReader.getCards();
    }
}
