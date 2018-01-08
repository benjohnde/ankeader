package io.github.benjohnde.ankeader.parser;

import io.github.benjohnde.ankeader.anki.ApkgBase;
import io.github.benjohnde.ankeader.anki.ApkgCard;
import io.github.benjohnde.ankeader.parser.orm.CardEntity;
import io.github.benjohnde.ankeader.parser.orm.CollectionReader;
import io.github.benjohnde.ankeader.parser.utils.FileUtils;
import io.github.benjohnde.ankeader.parser.utils.JsonUtils;
import io.github.benjohnde.ankeader.parser.utils.ZipUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApkgReader {
    private String input, output;
    private File tmp;

    private ApkgBase base;

    public ApkgReader(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public ApkgBase getBase() {
        return base;
    }

    public void run() throws Exception {
        this.base = new ApkgBase();
        unleashApkgContents();
        readMedia();
        processMedia();
        readSqlite();
        cleanup();
    }

    private void unleashApkgContents() throws IOException {
        tmp = FileUtils.serve(this.input);
        System.out.println("Operating on: " + tmp);
        ZipUtils.unzip(this.input, tmp);
    }

    private void cleanup() throws IOException {
        FileUtils.remove(tmp);
    }

    private void readMedia() throws IOException {
        String jsonFile = new String(Files.readAllBytes(Paths.get(tmp.getAbsolutePath(), "media")));
        Map<String, String> media = JsonUtils.getMapFromJson(jsonFile);
        this.base.setMedia(media);
    }

    private void processMedia() {
        this.base.getMedia().forEach((k, v) -> {
            File output = new File(this.output, "media");
            output.mkdirs();

            Path src = Paths.get(tmp.getAbsolutePath(), k);
            Path dest = Paths.get(output.getAbsolutePath(), v);
            try {
                Files.move(src, dest);
            } catch (IOException e) {
                // file may be not present
            }
        });
    }

    private void readSqlite() throws Exception {
        CollectionReader collectionReader = new CollectionReader(tmp.getAbsolutePath());
        List<CardEntity> cards = collectionReader.getCards();
        List<ApkgCard> apkgCardList = cards.stream().map(c -> new ApkgCard(c)).collect(Collectors.toList());
        this.base.setCards(apkgCardList);
    }
}
