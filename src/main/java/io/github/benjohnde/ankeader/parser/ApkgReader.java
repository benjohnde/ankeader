package io.github.benjohnde.ankeader.parser;

import io.github.benjohnde.ankeader.anki.ApkgMedia;
import io.github.benjohnde.ankeader.parser.orm.CollectionReader;
import io.github.benjohnde.ankeader.parser.utils.FileUtils;
import io.github.benjohnde.ankeader.parser.utils.JsonUtils;
import io.github.benjohnde.ankeader.parser.utils.ZipUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class ApkgReader {
    private String input, output;
    private File tmp;
    private ApkgMedia media;

    public ApkgReader(String input, String output) throws Exception {
        this.input = input;
        this.output = output;
    }

    private void run() throws Exception {
        tmp = FileUtils.serve(this.input);
        System.out.println("Operating on: " + tmp);
        ZipUtils.unzip(this.input, tmp);
        prepareApkgBase();
        readMedia();
        readSqlite();
        FileUtils.remove(tmp);
    }

    public void prepareApkgBase() {

    }

    public void readMedia() throws IOException {
        String jsonFile = new String(Files.readAllBytes(Paths.get(tmp.getAbsolutePath(), "media")));
        Map<String, String> media = JsonUtils.getMapFromJson(jsonFile);
        this.media = new ApkgMedia(media, tmp.getAbsolutePath());
        // TODO rename media
    }

    public void readSqlite() throws Exception {
        CollectionReader collectionReader = new CollectionReader(tmp.getAbsolutePath());
        collectionReader.getCards();
    }
}
