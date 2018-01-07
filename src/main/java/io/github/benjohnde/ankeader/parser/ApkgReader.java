package io.github.benjohnde.ankeader.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.benjohnde.ankeader.anki.ApkgMedia;
import io.github.benjohnde.ankeader.parser.utils.FileUtils;
import io.github.benjohnde.ankeader.parser.utils.ZipUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class ApkgReader {
    private String filename;
    private File tmp;
    private ApkgMedia media;

    public ApkgReader(String filename) throws Exception {
        this.filename = filename;
        tmp = FileUtils.serve(filename);
        System.out.println("Operating on: " + tmp);
        ZipUtils.unzip(filename, tmp);
        prepareApkgBase();
        readMedia();
        readSqlite();
        buildApkgTree();
        FileUtils.remove(tmp);
    }

    public void prepareApkgBase() {

    }

    public void readMedia() throws IOException {
        String jsonFile = new String(Files.readAllBytes(Paths.get(tmp.getAbsolutePath(), "media")));
        Type type = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> media = new Gson().fromJson(jsonFile, type);
        this.media = new ApkgMedia(media, tmp.getAbsolutePath());
    }

    public void readSqlite() throws Exception {
        CollectionReader collectionReader = new CollectionReader(tmp.getAbsolutePath());
    }

    public void buildApkgTree() {

    }
}
