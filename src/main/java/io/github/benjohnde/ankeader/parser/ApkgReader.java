package io.github.benjohnde.ankeader.parser;

import io.github.benjohnde.ankeader.anki.*;
import io.github.benjohnde.ankeader.parser.orm.CollectionReader;
import io.github.benjohnde.ankeader.parser.utils.FileUtils;
import io.github.benjohnde.ankeader.parser.utils.JsonUtils;
import io.github.benjohnde.ankeader.parser.utils.ZipUtils;

import io.github.benjohnde.ankeader.parser.orm.CardEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ApkgReader {
    private String input, output;
    private File tmp;

    private ApkgMedia media;
    private List<CardEntity> cards;

    private ApkgBase base;

    public ApkgReader(String input, String output) throws Exception {
        this.input = input;
        this.output = output;
        this.run();
    }

    private void run() throws Exception {
        tmp = FileUtils.serve(this.input);
        System.out.println("Operating on: " + tmp);
        ZipUtils.unzip(this.input, tmp);
        readMedia();
        readSqlite();
        prepareApkgBase();
        FileUtils.remove(tmp);
    }

    private void prepareApkgBase() {
      List<ApkgCard> apkgCards = new ArrayList<>();
      for (CardEntity cardEntity : this.cards) {
        apkgCards.add(new ApkgCard(cardEntity));
      }
      this.base = new ApkgBase(apkgCards, this.media);
      System.out.println(this.base);
    }

    private void readMedia() throws IOException {
        String jsonFile = new String(Files.readAllBytes(Paths.get(tmp.getAbsolutePath(), "media")));
        Map<String, String> media = JsonUtils.getMapFromJson(jsonFile);
        this.media = new ApkgMedia(media, tmp.getAbsolutePath());
        // TODO rename media
    }

    private void readSqlite() throws Exception {
        CollectionReader collectionReader = new CollectionReader(tmp.getAbsolutePath());
        this.cards = collectionReader.getCards();
    }
}
